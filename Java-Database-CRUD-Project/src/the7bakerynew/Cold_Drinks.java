package the7bakerynew;

/**
 *
 * @author ASUS
 */
public class Cold_Drinks extends Order_Info{
      public  String AddSugar;
    public  String MilkType;
    public  String Size;
    public  String Ice_Shaped;

    public Cold_Drinks(String AddSugar, String MilkType, String Size, String Ice_Shaped, int Order_Info_ID, Order order, Items items, int Quantity, double Item_price) {
        super(Order_Info_ID, order, items, Quantity, Item_price);
        this.AddSugar = AddSugar;
        this.MilkType = MilkType;
        this.Size = Size;
        this.Ice_Shaped = Ice_Shaped;
    }

    public Cold_Drinks(String AddSugar, String MilkType, String Size, String Ice_Shaped) {
        this.AddSugar = AddSugar;
        this.MilkType = MilkType;
        this.Size = Size;
        this.Ice_Shaped = Ice_Shaped;
    }

    

    public Cold_Drinks() {
    }
  
}
