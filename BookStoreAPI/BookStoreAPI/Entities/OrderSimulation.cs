namespace BookStoreAPI.Entities
{
    public class OrderSimulation
    {
        public OrderSimulation() {}
        public void SimulateOrderProcess(Order order)
        {
            order.ProcessOrder();
           
            Thread.Sleep(2000); 
            order.ShipOrder();

            Thread.Sleep(2000);
            order.DeliverOrder();
        }
    }
}
