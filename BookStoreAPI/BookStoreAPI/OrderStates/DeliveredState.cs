using BookStoreAPI.Entities;
using BookStoreAPI.Interfaces;

namespace BookStoreAPI.OrderStates
{
    public class DeliveredState : IOrderState
    {
        private static DeliveredState instance = null;

        private DeliveredState() { }

        public static DeliveredState Create()
        {
            if (instance == null)
            {
                instance = new DeliveredState();
            }
            return instance;
        }

        public void ProcessOrder(Order order)
        {
            throw new InvalidOperationException("Cannot process after delivery.");
        }

        public void ShipOrder(Order order)
        {
            throw new InvalidOperationException("Cannot ship after delivery.");
        }

        public void DeliverOrder(Order order)
        {
            var message = $"Order {order.Id} is already delivered.";
            Console.WriteLine(message);
            order.NotifyObservers(message);
        }
    }


}
