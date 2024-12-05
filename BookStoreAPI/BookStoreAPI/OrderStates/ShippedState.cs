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
            order.NotifyObservers();
        }

        public void ShipOrder(Order order)
        {
            order.OrderMessages.Add("Order is already shipped.");
            order.NotifyObservers();
        }

        public void DeliverOrder(Order order)
        {
            order.State = DeliveredState.Create();
            order.OrderMessages.Add("Order is delivered.");
            order.NotifyObservers();
        }
    }


}
