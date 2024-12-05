using BookStoreAPI.OrderStates;

namespace BookStoreAPI.Entities
{
    public class Customer : IObserver
    {
        //Atributes
        public int Id { get; set; }
        public string? Name { get; set; }
        public string? Password { get; set; }
        public string? Email { get; set; }
        public string? PhoneNumber { get; set; }
        public string? Address { get; set; }
        public List<Order> Orders { get; set; } = new List<Order>();

        public void Update(Order order)
        {
            order.OrderMessages
        }

    }
}
