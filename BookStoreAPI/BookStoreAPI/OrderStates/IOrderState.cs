using System.Threading.RateLimiting;

namespace BookStoreAPI.Interfaces
{
    public interface IOrderState
    {
        //Methods
        void  processOrder();
        void shipOrder();
        void deliverOrder();

    }
}
