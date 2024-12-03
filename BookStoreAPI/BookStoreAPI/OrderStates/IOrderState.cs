using System.Threading.RateLimiting;

namespace BookStoreAPI.Interfaces
{
    public interface IOrderState
    {
        //Atributes
        string orderStatusInfo;
        //Methods
        void  processOrder();
        void shipOrder();
        void deliverOrder();

    }
}
