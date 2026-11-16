package IMR;

//Shahad Almalki
public class Drink extends MenuItems {

    public Drink(String itemName, String classification, double price) {
        super(itemName, classification, price); //the Drinks constructor will call the super class's constructor

    }

    //overridden to add drink name and drink type
    @Override
    public String toString() {
        return "Drink name: "+super.getItemName()+super.toString();
    }
}