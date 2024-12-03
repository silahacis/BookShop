namespace BookStoreAPI.Entities
{
    public class Cart
    {
        //Atributes
        //Code first db için başlarına bi şeyler eklenecek
        public int Id { get; set; }
        public int CustomerId { get; set; }
        List<Book> books { get; set; }
        public double TotalPrice { get; set; }


    }
}
