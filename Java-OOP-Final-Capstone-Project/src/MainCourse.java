
package IMR;

//Danah Alharbi
public class MainCourse extends MenuItems {

    public MainCourse(String itemName, String classification, double price) {
        super(itemName, classification, price); //the main course constructor will call the super class's constructor
    }

    //overridden to add main course name
    @Override
    public String toString(){
        return "Main course name: "+super.getItemName()+super.toString();
    }

}