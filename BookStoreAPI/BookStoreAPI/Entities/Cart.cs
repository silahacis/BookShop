namespace BookStoreAPI.Entities
{
    public class Cart 
    {
        //Atributes
        public int Id { get; set; }
        public int CustomerId { get; set; }
        public Customer Customer { get; set; }
        public double TotalPrice { get; set; }


    }
}
