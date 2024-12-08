using BookStoreAPI.Entities;
using BookStoreAPI.Repositories;
using Microsoft.AspNetCore.Mvc;

namespace BookStoreAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class CustomersController : ControllerBase
    {
        private readonly CustomerRepository _repository;

        public CustomersController(CustomerRepository repository)
        {
            _repository = repository;
        }

        [HttpGet]

        [HttpGet("{id}")]
        public ActionResult<Customer> GetById(int id)
        {
            var customer = _repository.GetById(id);
            if (customer == null)
                return NotFound();

            return customer;
        }

        [HttpPost]
        public IActionResult Add(Customer customer)
        {
            _repository.Add(customer);
            return CreatedAtAction(nameof(GetById), new { id = customer.Id }, customer);
        }

        [HttpPut("{id}")]
        public IActionResult Update(int id, Customer customer)
        {
            if (id != customer.Id)
                return BadRequest();

            _repository.Update(customer);
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
