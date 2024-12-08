using BookStoreAPI.Entities;

namespace BookStoreAPI.Repositories
{
    public class BookRepository
    {
        private readonly List<Book> _books;

        public BookRepository()
        {
            _books = new List<Book>
            {
                new Book
                {
                    Id = 1,
                    Title = "The Great Gatsby",
                    Author = "F. Scott Fitzgerald",
                    Price = 10.99,
                    Stock = 50,
                    Categories = new List<Category>
                    {
                        new Category { Id = 1, Name = "Fiction" }
                    }
                },
                new Book
                {
                    Id = 2,
                    Title = "A Brief History of Time",
                    Author = "Stephen Hawking",
                    Price = 15.99,
                    Stock = 20,
                    Categories = new List<Category>
                    {
                        new Category { Id = 2, Name = "Science" }
                    }
                },
                new Book
                {
                    Id = 3,
                    Title = "Sapiens: A Brief History of Humankind",
                    Author = "Yuval Noah Harari",
                    Price = 12.49,                    Stock = 35,
                    Categories = new List<Category>
                    {
                        new Category { Id = 3, Name = "History" },
                        new Category { Id = 2, Name = "Science" }
                    }
                }
            };
        }

        public List<Book> GetAll() => _books;

        public Book? GetById(int id) => _books.FirstOrDefault(b => b.Id == id);

        public List<Book> GetByCategoryId(int categoryId)
        {
            return _books.Where(book => book.Categories.Any(category => category.Id == categoryId)).ToList();
        }

        public void Add(Book book)
        {
            book.Id = _books.Max(b => b.Id) + 1;
            _books.Add(book);
        }

        public void Update(Book book)
        {
            var existingBook = GetById(book.Id);
            if (existingBook != null)
            {
                existingBook.Title = book.Title;
                existingBook.Author = book.Author;
                existingBook.Price = book.Price;
                existingBook.Stock = book.Stock;
                existingBook.Categories = book.Categories;
            }
        }

        public void Delete(int id)
        {
            var book = GetById(id);
            if (book != null)
            {
                _books.Remove(book);
            }
        }
    }
}
