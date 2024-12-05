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
            order.OrderMessages.Add($"Order {order.Id} is pending and being processed.");
        }

        public void ShipOrder(Order order)
        {
            order.State = ShippedState.Create();
            order.OrderMessages.Add("Order is now shipped.");
        }

        public void DeliverOrder(Order order)
        {
            throw new InvalidOperationException("Cannot deliver before shipping.");
        }
    }


}
