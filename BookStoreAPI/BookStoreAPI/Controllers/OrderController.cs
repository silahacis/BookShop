using BookStoreAPI.Entities;
using BookStoreAPI.Repositories;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

namespace BookStoreAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class OrdersController : ControllerBase
    {
        private readonly OrderRepository _repository;

        public OrdersController(OrderRepository repository)
        {
            _repository = repository;
        }

        [HttpGet]
        public ActionResult<List<Order>> GetAll()
        {
            return _repository.GetAll();
        }

        [HttpGet("{id}")]
        public ActionResult<Order> GetById(int id)
        {
            var order = _repository.GetById(id);
            if (order == null)
                return NotFound();

            return order;
        }

        [HttpGet("customer/{customerId}")]
        public ActionResult<List<Order>> GetByCustomerId(int customerId)
        {
            var orders = _repository.GetByCustomerId(customerId);

            if (orders == null || orders.Count == 0)
                return NotFound(new { Message = $"No orders found for customer with ID {customerId}." });

            return orders;
        }


        [HttpPost]
        public IActionResult Add(Order order)
        {
            _repository.Add(order);
            return CreatedAtAction(nameof(GetById), new { id = order.Id }, order);
        }

        [HttpPut("{id}")]
        public IActionResult Update(int id, Order order)
        {
            if (id != order.Id)
                return BadRequest();

            _repository.Update(order);
            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            _repository.Delete(id);
            return NoContent();
        }
    }
}
