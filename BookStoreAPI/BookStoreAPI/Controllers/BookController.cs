using BookStoreAPI.Entities;
using BookStoreAPI.Repositories;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

namespace BookStoreAPI.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class BooksController : ControllerBase
    {
        private readonly BookRepository _repository;

        public BooksController(BookRepository repository)
        {
            _repository = repository;
        }

        [HttpGet]
        public ActionResult<List<Book>> GetAll()
        {
            return _repository.GetAll();
        }

        [HttpGet("{id}")]
        public ActionResult<Book> GetById(int id)
        {
            var book = _repository.GetById(id);
            if (book == null)
                return NotFound();

            return book;
        }

        [HttpGet("category/{categoryId}")]
        public ActionResult<List<Book>> GetByCategoryId(int categoryId)
        {
            var books = _repository.GetByCategoryId(categoryId);

            if (books == null || books.Count == 0)
                return NotFound(new { Message = $"No books found for category with ID {categoryId}." });

            return books;
        }


        [HttpPost]
        public IActionResult Add(Book book)
        {
            _repository.Add(book);
            return CreatedAtAction(nameof(GetById), new { id = book.Id }, book);
        }

        [HttpPut("{id}")]
        public IActionResult Update(int id, Book book)
        {
            if (id != book.Id)
                return BadRequest();

            _repository.Update(book);
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
