using Microsoft.AspNetCore.Mvc;
using BookStoreAPI.Entities;
using BookStoreAPI.Requests;
using BookStoreAPI.Responses;
using BookStoreAPI.Payments;
using BookStoreAPI.Invoices;
using BookStoreAPI.Repositories;


namespace BookStoreAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class BookShopController : ControllerBase
    {
        private readonly CustomerRepository _repository;

        public BookShopController(CustomerRepository repository)
        {
            _repository = repository;
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
                var customer = _repository.GetById(request.CustomerId);

                var response = Order.CreateOrder(
                    request.Books,
                    customer,
                    paymentProcessor,
                    invoiceFactory,
                    request.InvoiceType,
                    request.OrderAddress
                );

                var orderSimulation = new OrderSimulation();
                orderSimulation.SimulateOrderProcess(response.Order);

                return CreatedAtAction(nameof(GetOrder), new { id = response.Order.Id }, response);
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }


        [HttpGet("{id}")]
        public IActionResult GetOrder(int id)
        {
            return Ok($"Order details for ID {id} (placeholder).");
        }
    }
}
