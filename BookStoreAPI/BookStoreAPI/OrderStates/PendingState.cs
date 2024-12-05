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
            return instance;
        }

        public void ProcessOrder(Order order)
        {
            var message = $"Order {order.Id} is pending and being processed.";
            order.NotifyObservers(message);
        }

        public void ShipOrder(Order order)
        {
            order.State = ShippedState.Create();
            var message = $"Order {order.Id} is now shipped.";
            order.NotifyObservers(message);
        }

        public void DeliverOrder(Order order)
        {
            throw new InvalidOperationException("Cannot deliver before shipping.");
        }
    }


}
