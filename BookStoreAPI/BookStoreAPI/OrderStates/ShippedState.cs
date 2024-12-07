using BookStoreAPI.Entities;
using BookStoreAPI.Interfaces;

namespace BookStoreAPI.OrderStates
{
    public class ShippedState : IOrderState
    {
        private static ShippedState instance = null;

        private ShippedState() { }

        public static ShippedState Create()
        {
            if (instance == null)
            {
                instance = new ShippedState();
            }
            Console.WriteLine("Shipped state is created only once");
            return instance;
        }

        public void ProcessOrder(Order order)
        {
            throw new InvalidOperationException("Cannot process an order that has already been shipped.");
        }


        public void ShipOrder(Order order)
        {
            var message = $"Order {order.Id} is already shipped.";
            Console.WriteLine(message);
            order.NotifyObservers(message);
        }

        public void DeliverOrder(Order order)
        {
            order.State = DeliveredState.Create();
            var message = $"Order {order.Id} is delivered.";
            Console.WriteLine();
            order.NotifyObservers(message);
        }
    }


}
