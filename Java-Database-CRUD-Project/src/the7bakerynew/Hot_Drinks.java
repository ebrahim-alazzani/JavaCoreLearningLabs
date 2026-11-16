package the7bakerynew;


public class Hot_Drinks extends Order_Info {

    public String AddSugar;
    public String Size;
    public String MilkType;
    public String ExtraHot;

    public Hot_Drinks(String AddSugar, String Size, String MilkType, String ExtraHot, int Order_Info_ID, Order order, Items items, int Quantity, double Item_price) {
        super(Order_Info_ID, order, items, Quantity, Item_price);
        this.AddSugar = AddSugar;
        this.Size = Size;
        this.MilkType = MilkType;
        this.ExtraHot = ExtraHot;
    }

    public Hot_Drinks(String AddSugar, String Size, String MilkType, String ExtraHot, String Flavor) {
        this.AddSugar = AddSugar;
        this.Size = Size;
        this.MilkType = MilkType;
        this.ExtraHot = ExtraHot;
    }

 
    public Hot_Drinks() {
    }
}
 
