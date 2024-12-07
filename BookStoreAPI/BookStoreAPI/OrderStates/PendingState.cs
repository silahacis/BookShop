using BookStoreAPI.Entities;
using BookStoreAPI.Interfaces;

namespace BookStoreAPI.OrderStates
{
    public class PendingState : IOrderState
    {
        private static PendingState instance = null;

        private PendingState() { }

        public static PendingState Create()
        {
            if (instance == null)
            {
                instance = new PendingState();
            }
            Console.WriteLine("Pending state is created only once");
            return instance;
        }

        public void ProcessOrder(Order order)
        {
            var message = $"Order {order.Id} is pending and being processed.";
            Console.WriteLine(message);
            order.NotifyObservers(message);
        }

        public void ShipOrder(Order order)
        {
            order.State = ShippedState.Create();
            var message = $"Order {order.Id} is now shipped.";
            Console.WriteLine(message);
            order.NotifyObservers(message);
        }

        public void DeliverOrder(Order order)
        {
            throw new InvalidOperationException("Cannot deliver before shipping.");
        }
    }


}
