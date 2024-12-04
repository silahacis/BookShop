namespace BookStoreAPI.Entities
{
    public class Customer
    {
        //Atributes
        public int Id { get; set; }
        public string Name { get; set; }
        public string Password { get; set; }
        public string Email { get; set; }
        public string PhoneNumber { get; set; }
        public string Address { get; set; }
        public int CartId { get; set; }
        public Cart Cart { get; set; }
        public List<Order> Orders { get; set; }

    }
}
