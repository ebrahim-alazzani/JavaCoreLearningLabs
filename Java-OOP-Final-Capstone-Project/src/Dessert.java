
package IMR;
//Shahad Almalki
public class Dessert extends MenuItems {

    public Dessert(String itemName, String classification, double price) {
        super(itemName, classification, price);//the dessert constructor will call the super class's constructor
    }

    //overridden to add dessert's name
    @Override
    public String toString(){
        return "Dessert name: "+super.getItemName()+super.toString();
    }
}
