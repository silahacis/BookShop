﻿namespace BookStoreAPI.Entities
{
    public class Book
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Author { get; set; }
        public double Price { get; set; }
        public Category Category { get; set; }
        public int Stock { get; set; }
    }
}
