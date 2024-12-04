namespace BookStoreAPI.Entities
{
    public abstract class BaseProduct
    {
        public int Id { get; set; }
        public DateTime CreatedDate { get; set; }
        public DateTime? UpdatedDate { get; set; }
        public double Price { get; set; }
        public virtual ICollection<Category> Categories { get; set; }
        public int Stock { get; set; }
    }
}
