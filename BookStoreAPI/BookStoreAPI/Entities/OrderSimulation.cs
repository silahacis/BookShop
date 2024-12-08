namespace BookStoreAPI.Entities
{
    public class OrderSimulation
    {
        public OrderSimulation() {}
        public async Task SimulateOrderProcessAsync(Order order)
        {
            order.ProcessOrder();

            await Task.Delay(5000);

            order.ShipOrder();

            await Task.Delay(5000); 

            order.DeliverOrder();
        }

    }
}
