
package IMR;
//Danah Alharbi
public class Salad extends MenuItems {

    public Salad(String itemName, String classification, double price) {
        super(itemName, classification, price); //the salad constructor will call the super class's constructor
    }

    //overridden to add Salad's name
    @Override
    public String toString(){
        return "Salad name: "+super.getItemName()+super.toString();
    }
}