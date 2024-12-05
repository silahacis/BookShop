namespace BookStoreAPI.Entities
{
    public class Book
    {
        public int Id { get; set; } 
        public DateTime CreatedDate { get; set; }
        public DateTime? UpdatedDate { get; set; }
        public double Price { get; set; }
        public virtual ICollection<Category> Categories { get; set; } = new List<Category>();
        public int Stock { get; set; }
        public string? Title { get; set; }
        public string? Author { get; set; }
    }
}
