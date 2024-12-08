using BookStoreAPI.Entities;
using System.Collections.Generic;
using System.Linq;

namespace BookStoreAPI.Repositories
{
    public class CategoryRepository
    {
        private readonly List<Category> _categories;

        public CategoryRepository()
        {
            _categories = new List<Category>
            {
                new Category { Id = 1, Name = "Fiction" },
                new Category { Id = 2, Name = "Science" },
                new Category { Id = 3, Name = "History" },
                new Category { Id = 4, Name = "Horror" },
                new Category { Id = 5, Name = "Mystery" },
                new Category { Id = 6, Name = "Religous" }

            };
        }

        public List<Category> GetAll() => _categories;

        public Category? GetById(int id) => _categories.FirstOrDefault(c => c.Id == id);

        public void Add(Category category)
        {
            category.Id = _categories.Max(c => c.Id) + 1;
            _categories.Add(category);
        }

        public void Delete(int id)
        {
            var category = GetById(id);
            if (category != null)
            {
                _categories.Remove(category);
            }
        }
    }
}
