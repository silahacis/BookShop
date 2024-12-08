using BookStoreAPI.Entities;
using BookStoreAPI.Payments;
using BookStoreAPI.Repositories;
using BookStoreAPI.Requests;
using BookStoreAPI.Responses;
using Microsoft.AspNetCore.Mvc;

namespace BookStoreAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class OrdersController : ControllerBase
    {
        private readonly OrderRepository _orderRepository;
        private readonly CustomerRepository _customerRepository;
        public OrdersController(OrderRepository repository, CustomerRepository customerRepository)
        {
            _orderRepository = repository;
            _customerRepository = customerRepository;
        }

        [HttpPost]
        public IActionResult CreateOrder([FromBody] CreateOrderRequest request)
        {
            if (request == null || request.Books == null || !request.Books.Any())
            {
                return BadRequest("Invalid order data.");
            }

            try
            {
                var invoiceFactory = new ConcreteInvoiceFactory();
                var paymentProcessor = new PaymentProcessor(request.PaymentMethod);
                var customer = _customerRepository.GetById(request.CustomerId);

                if (customer == null)
                {
                    return NotFound("Customer not found.");
                }

                var response = Order.CreateOrder(
                    request.Books,
                    customer,
                    paymentProcessor,
                    invoiceFactory,
                    request.InvoiceType,
                    request.OrderAddress
                );

                _orderRepository.Add(response.Order);

                return Ok(response.Order);
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }


        [HttpPost("{id}/simulate")]
        public async Task<IActionResult> SimulateOrder(int id)
        {
            var order = _orderRepository.GetById(id);
            if (order == null)
            {
                return NotFound($"Order with ID {id} not found.");
            }

            try
            {
                var orderSimulation = new OrderSimulation();
                await orderSimulation.SimulateOrderProcessAsync(order);

                return Ok($"Order with ID {id} has been successfully simulated.");
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpGet]
        public ActionResult<List<Order>> GetAll()
        {
            return _orderRepository.GetAll();
        }

        [HttpGet("{id}")]
        public ActionResult<Order> GetById(int id)
        {
            var order = _orderRepository.GetById(id);
            if (order == null)
                return NotFound();

            return order;
        }

        [HttpGet("customer/{customerId}")]
        public ActionResult<List<GetOrderResponse>> GetByCustomerId(int customerId)
        {
            var orders = _orderRepository.GetByCustomerId(customerId);

            if (orders == null || orders.Count == 0)
                return NotFound(new { Message = $"No orders found for customer with ID {customerId}." });

            var orderResponses = orders.Select(order => new GetOrderResponse
            {
                Id = order.Id,
                Books = order.Books,
                TotalAmount = order.Books.Sum(book => book.Price),
                OrderAddress = order.OrderAddress,
                OrderDate = order.OrderDate,
                OrderMessages = order.OrderMessages,
                InvoiceType = order.Invoice.GetType().Name, 
                PaymentMethod = order.PaymentStrategy.GetType().Name 
            }).ToList();

            return orderResponses;
        }


        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            _orderRepository.Delete(id);
            return NoContent();
        }
    }
}
