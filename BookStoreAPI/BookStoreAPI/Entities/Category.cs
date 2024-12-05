﻿namespace BookStoreAPI.Entities
{
    public class Category
    {
        public int Id { get; set; } 
        public string? Name { get; set; }
        public virtual ICollection<Book> Books { get; set; } = new List<Book>();
    }
}
