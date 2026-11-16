package the7bakerynew;


public class Order_Info {

    private int Order_Info_ID;
    private Order order;
    private Items items;
    private int Quantity;
    private double Item_price;

    public Order_Info(int Order_Info_ID, Order order, Items items, int Quantity, double Item_price) {
        this.Order_Info_ID = Order_Info_ID;
        this.order = order;
        this.items = items;
        this.Quantity = Quantity;
        this.Item_price = Item_price;
    }

    public Order_Info() {
    }

    public int getOrder_Info_ID() {
        return Order_Info_ID;
    }

    public void setOrder_Info_ID(int Order_Info_ID) {
        this.Order_Info_ID = Order_Info_ID;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getItem_price() {
        return Item_price;
    }

    public void setItem_price(double Item_price) {
        this.Item_price = Item_price;
    }

   
}