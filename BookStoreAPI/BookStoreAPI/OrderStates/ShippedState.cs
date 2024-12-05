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
            return instance;
        }

        public void ProcessOrder(Order order)
        {
            throw new InvalidOperationException("Cannot process an order that has already been shipped.");
        }


        public void ShipOrder(Order order)
        {
            order.State = DeliveredState.Create();
            var message = "Order is already shipped.";
            order.NotifyObservers(message);
        }

        public void DeliverOrder(Order order)
        {  
            var message = "Order is delivered.";
            order.NotifyObservers(message);
        }
    }


}
