namespace BookStoreAPI.Entities
{
    public class Book
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string Author { get; set; }
        public double Price { get; set; }
        public int CategoryId { get; set; }
        public virtual ICollection<Category> Categories { get; set; }
        public int Stock { get; set; }
    }
}
