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

        public void Update(Order order, string message)
        {
            order.OrderMessages.Add(message);
            Console.WriteLine("Güncellenmiş Mesaj Listesi:");
            foreach (var msg in order.OrderMessages)
            {
                Console.WriteLine($"- {msg}");
            }
        }

    }
}
