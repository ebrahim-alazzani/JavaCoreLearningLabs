package the7bakerynew;


public class Sandwich extends Order_Info{
    
    public  String Spicy;
    public  String BreadType;
    public  String ProteinType;
    public Sandwich(String Spicy, String BreadType, String ProteinType, 
            int Order_Info_ID, Order order, Items items, int Quantity, double Item_price) {
        super(Order_Info_ID, order, items, Quantity, Item_price);
        this.Spicy = Spicy;
        this.BreadType = BreadType;
        this.ProteinType = ProteinType;
    }

    public Sandwich(String Spicy, String BreadType, String ProteinType) {
        this.Spicy = Spicy;
        this.BreadType = BreadType;
        this.ProteinType = ProteinType;
    }

   
    public Sandwich() {
    }

   
}