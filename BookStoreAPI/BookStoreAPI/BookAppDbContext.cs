using BookStoreAPI.Entities;
using BookStoreAPI.Invoices;
using BookStoreAPI.OrderStates;
using BookStoreAPI.Payments;
using Microsoft.EntityFrameworkCore;

namespace BookStoreAPI
{
    public class BookAppDbContext : DbContext
    {
        public BookAppDbContext(DbContextOptions<BookAppDbContext> options) : base(options) { }

        public DbSet<Book> Books { get; set; }
        public DbSet<Order> Orders { get; set; }
        public DbSet<Category> Categories { get; set; }
        public DbSet<Customer> Customers { get; set; }

        public DbSet<DeliverState> DeliverStates { get; set; }
        public DbSet<PendingState> PendingStates { get; set; }
        public DbSet<ShipState> ShipStates { get; set; }

        public DbSet<PhysicalInvoice> PhysicalInvoices { get; set; }
        public DbSet<DigitalInvoice> DigitalInvoices { get; set; }

        public DbSet<CreditCardPayment> CreditCardPayments { get; set; }
        public DbSet<PayPalPayment> PayPalPayments { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            // Configure the polymorphic relationship for IPaymentStrategy
            modelBuilder.Entity<Order>()
                .HasOne(o => o.PaymentStrategy)
                .WithMany()  // No back-reference to Order
                .HasForeignKey("PaymentStrategyId"); // Optional: if you want a FK column

            // TPH configuration (Table Per Hierarchy) for polymorphism
            modelBuilder.Entity<CreditCardPayment>().ToTable("Orders");
            modelBuilder.Entity<PayPalPayment>().ToTable("Orders");

            // Add a discriminator to indicate which type the row corresponds to
            modelBuilder.Entity<Order>()
                .HasDiscriminator<string>("PaymentType")
                .HasValue<CreditCardPayment>("CreditCard")
                .HasValue<PayPalPayment>("PayPal");
        }




    }
}
