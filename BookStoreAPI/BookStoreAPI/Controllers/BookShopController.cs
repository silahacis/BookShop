using Microsoft.AspNetCore.Mvc;
using BookStoreAPI.Entities;
using BookStoreAPI.Requests;
using BookStoreAPI.Responses;
using BookStoreAPI.Payments;
using BookStoreAPI.Invoices;


namespace BookStoreAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class BookShopController : ControllerBase
    {
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

                var response = Order.CreateOrder(
                    request.Books,
                    request.Customer,
                    paymentProcessor,
                    invoiceFactory,
                    request.InvoiceType
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
