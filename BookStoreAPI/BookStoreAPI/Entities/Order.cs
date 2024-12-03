namespace BookStoreAPI.Entities
{
    public class Order
    {
        //Atributes
        public int Id { get; set; }
        public List<OrderItem> OrderItems { get; set; }
        public double TotalAmount { get; set; }
        public int CustomerId { get; set; }
        public OrderStatus orderStatus { get; set; }
        

    }
}
