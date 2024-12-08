using BookStoreAPI.Entities;

namespace BookStoreAPI.Interfaces
{
    public interface IOrderState
    {
        void  ProcessOrder(Order order);
        void ShipOrder(Order order);
        void DeliverOrder(Order order);

    }
}
