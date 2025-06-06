﻿using BookStoreAPI.Entities;
using BookStoreAPI.Invoices;
using BookStoreAPI.Interfaces;
using BookStoreAPI.OrderStates;
using BookStoreAPI.Payments;
using BookStoreAPI.Enums;
using System.Collections.Generic;
using System.Linq;

namespace BookStoreAPI.Repositories
{
    public class OrderRepository
    {
        private readonly List<Order> _orders;

        public OrderRepository(BookRepository bookRepository, CustomerRepository customerRepository)
        {
            var books = bookRepository.GetAll();
            var customer = customerRepository.GetById(1);

            if (customer == null)
                throw new Exception("No sample customer found for orders.");

            _orders = new List<Order>
                {
                    new Order
                    {
                        Id = 1001,
                        Books = new List<Book> { books[0], books[1] },
                        TotalAmount = books[0].Price + books[1].Price,
                        Customer = customer,
                        OrderDate = DateTime.Now.AddDays(-10),
                        State = PendingState.Create(),
                        Invoice = new DigitalInvoice(),
                        OrderMessages = new List<string> {"Order 1001 is pending and being processed.","Order 1001 is now shipped.","Order 1001 is delivered." },
                        OrderAddress = "street: 123 Elm Street, city: Springfield, state: IL, zipCode: 62701, country: USA",
                        PaymentStrategy = new CreditCardPayment()
                    },
                    new Order
                    {
                        Id = 1002,
                        Books = new List<Book> { books[1], books[2] },
                        TotalAmount = books[1].Price + books[2].Price,
                        Customer = customer,
                        OrderDate = DateTime.Now.AddDays(-5),
                        State = ShippedState.Create(),
                        OrderMessages = new List<string> {"Order 1002 is pending and being processed.","Order 1002 is now shipped.","Order 1002 is delivered." }, 
                        OrderAddress = "street: 123 Elm Street, city: Springfield, state: IL, zipCode: 62701, country: USA",
                        Invoice = new PhysicalInvoice(),
                        PaymentStrategy = new PayPalPayment()
                    }
                };

        }

        public List<Order> GetAll() => _orders;

        public Order? GetById(int id) => _orders.FirstOrDefault(o => o.Id == id);

        public List<Order> GetByCustomerId(int customerId)
        {
            return _orders.Where(o => o.Customer.Id == customerId).ToList();
        }

        public void Add(Order order)
        {
            order.Id = GenerateOrderId();
            order.OrderDate = DateTime.Now;
            _orders.Add(order);
        }

        public void Update(Order order)
        {
            var existingOrder = GetById(order.Id);
            if (existingOrder != null)
            {
                existingOrder.Books = order.Books;
                existingOrder.TotalAmount = order.TotalAmount;
                existingOrder.Customer = order.Customer;
                existingOrder.Invoice = order.Invoice;
                existingOrder.PaymentStrategy = order.PaymentStrategy;
            }
        }

        public void Delete(int id)
        {
            var order = GetById(id);
            if (order != null)
            {
                _orders.Remove(order);
            }
        }

        private static int GenerateOrderId()
        {
            return new Random().Next(1000, 9999);
        }
    }
}
