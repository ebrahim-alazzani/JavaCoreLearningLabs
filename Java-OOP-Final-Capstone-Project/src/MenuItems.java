
package IMR;

//Danah Alharbi
public class MenuItems {
    private String itemName;
    private String classification;
    private double price;


    //constructor
    public MenuItems(String itemName, String classification, double price) {
        this.itemName = itemName;
        this.classification = classification;
        this.price = price;

    }

    //Getters
    public String getItemName() {
        return itemName;
    }

    public String getClassification() {
        return classification;
    }

    public double getPrice() {
        return price;
    }


    //Setters
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //toString method is overriden to display all atributes of menu items
    @Override
    public String toString(){
        return " ,Classification: "+classification+" ,price: "+price+" SR";

    }
}
