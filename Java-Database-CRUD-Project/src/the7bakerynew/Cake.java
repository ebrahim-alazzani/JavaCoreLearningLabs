package the7bakerynew;

/**
 *
 * @author ASUS
 */
public class Cake extends Order_Info{
public String Size;
public int Layer ;
public String Frosting ;
public String Flavor;

    public Cake(String Size, int Layer, 
            String Frosting, String Flavor, int Order_Info_ID, Order order, Items items, int Quantity, double Item_price) {
        super(Order_Info_ID, order, items, Quantity, Item_price);
        this.Size = Size;
        this.Layer = Layer;
        this.Frosting = Frosting;
        this.Flavor = Flavor;
    }

    public Cake(String Size, int Layer, String Frosting, String Flavor) {
        this.Size = Size;
        this.Layer = Layer;
        this.Frosting = Frosting;
        this.Flavor = Flavor;
    }

    

    



}