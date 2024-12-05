using BookStoreAPI.Entities;
using System.Collections.Generic;
using System.Linq;

namespace BookStoreAPI.Repositories
{
    public class CustomerRepository
    {
        private readonly List<Customer> _customers;

        public CustomerRepository()
        {
            // Initialize with dummy data
            _customers = new List<Customer>
            {
                new Customer
                {
                    Id = 1,
                    Name = "John Doe",
                    Password = "password123",
                    Email = "johndoe@example.com",
                    PhoneNumber = "123-456-7890",
                    Address = "123 Main Street",
                    Orders = new List<Order>(),
                    OrderMessage = "No orders yet."
                }
            };
        }

        public List<Customer> GetAll() => _customers;

        public Customer? GetById(int id) => _customers.FirstOrDefault(c => c.Id == id);

        public void Add(Customer customer)
        {
            customer.Id = _customers.Max(c => c.Id) + 1;
            _customers.Add(customer);
        }

        public void Update(Customer customer)
        {
            var existingCustomer = GetById(customer.Id);
            if (existingCustomer != null)
            {
                existingCustomer.Name = customer.Name;
                existingCustomer.Password = customer.Password;
                existingCustomer.Email = customer.Email;
                existingCustomer.PhoneNumber = customer.PhoneNumber;
                existingCustomer.Address = customer.Address;
                existingCustomer.Orders = customer.Orders;
                existingCustomer.OrderMessage = customer.OrderMessage;
            }
        }

        public void Delete(int id)
        {
            var customer = GetById(id);
            if (customer != null)
            {
                _customers.Remove(customer);
            }
        }
    }
}
