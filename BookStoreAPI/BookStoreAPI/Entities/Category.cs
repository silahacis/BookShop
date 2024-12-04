namespace BookStoreAPI.Entities
{
    public class Category
    {
        //Atributes
        public int Id { get; set; }
        public string Name { get; set; }
        public virtual ICollection<Book> Books { get; set; }
    }
}
