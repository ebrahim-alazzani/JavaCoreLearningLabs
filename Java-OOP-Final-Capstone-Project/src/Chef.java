
package IMR;

//Miral alghamdi and Walaa
import java.util.ArrayList;
import java.util.Scanner;
public class Chef extends User{

    static int choice;
    static Scanner input = new Scanner(System.in);

    public Chef(String userName, String userPos, String pass){
        this.userName = userName;
        this.userPos = userPos;
        super.setPass(pass);
    }

    public static void addFood(){
        System.out.println("which menu would you like to add in?");
        System.out.println("1-salad          2-main course      3-drink         4-dessert");
        choice = input.nextInt();

        switch(choice){
            case 1:
                //add to salad array list
                addSalad();
                System.out.println("salad added");
                break;
            case 2:
                //add to main course array list
                addMainCourse();
                System.out.println("main course added");
                break;
            case 3:
                //add to drinks array list
                addDrink();
                System.out.println("drink added");
                break;
            case 4:
                //add to dessert array list
                addDessert();
                System.out.println("dessert added");
                break;
            default:
                System.out.print("wrong entry");
        }
    }
    public static void deleteFood(){
        System.out.println("which menu would you like to delete from?");
        System.out.println("1-salad          2-main course      3-drink         4-dessert");
        choice = input.nextInt();

        switch(choice){
            case 1:
                //delete from salad array list
                deleteSalad();
                break;
            case 2:
                //delete from main course array list
                deleteMainCourse();
                break;
            case 3:
                //delete from drinks array list
                deleteDrink();
                break;
            case 4:
                //delete from dessert array list
                deleteDessert();
                break;
            default:
                System.out.print("wrong entry");
        }
    }


    public static void addSalad(){
        //add Salad to  salad array list

        input.nextLine();
        System.out.println("Enter Salad Name:");
        String saladName= input.nextLine();
        String classification = "Salad";
        System.out.println("Enter Price:");
        double price = input.nextDouble();
        Salad salad = new Salad(saladName, classification, price);
        IMR.saladlist.add(salad);
    }

    public static void addMainCourse(){
        //add nain course to main course array list

        input.nextLine();
        System.out.println("Enter Main Course Name:");
        String mainCourseName = input.nextLine();
        String classification = "Main Course";
        System.out.println("Enter Price:");
        double price = input.nextDouble();
        MainCourse mainCourse = new MainCourse(mainCourseName, classification, price);
        IMR.maincourselist.add(mainCourse);

    }
    public static void addDrink(){
        //add drink to drink array list

        input.nextLine();
        System.out.println("Enter Drink Name:");
        String drinkName = input.nextLine();
        String classification = "Drink";
        System.out.println("Enter Price:");
        double price = input.nextDouble();
        Drink drink = new Drink(drinkName, classification, price);
        IMR.drinkslist.add(drink);
    }
    public static void addDessert(){
        //add dessert to dessert array list

        input.nextLine();
        System.out.println("Enter Dessert Name: ");
        String dessertName = input.nextLine();
        String classification = "Dessert";
        System.out.println("Enter Price: ");
        double price = input.nextDouble();
        Dessert dessert = new Dessert(dessertName, classification, price);
        IMR.dessertlist.add(dessert);
    }
    public static void deleteSalad(){
        //display salad array list then delete Salad from salad array list

        for (Salad salad : IMR.saladlist) {
            System.out.println(salad);
        }
        input.nextLine();
        System.out.println("Enter Salad Name to delete:");
        String saladNameToDelete = input.nextLine();
        for (Salad salad : IMR.saladlist) {
            if (salad.getItemName().equalsIgnoreCase(saladNameToDelete)) {
                IMR.saladlist.remove(salad);
                System.out.println("Salad deleted successfully!");
                return;
            }
        }
        System.out.println("Salad not found.");
    }


    public static void deleteMainCourse(){
        //display maincourse array list then delete main course from  maincourse array list

        for (MainCourse mainCourse : IMR.maincourselist) {
            System.out.println(mainCourse);
        }
        input.nextLine();
        System.out.println("Enter Main Course Name to delete:");
        String mainCourseNameToDelete = input.nextLine();
        for (MainCourse mainCourse : IMR.maincourselist) {
            if (mainCourse.getItemName().equalsIgnoreCase(mainCourseNameToDelete)) {
                IMR.maincourselist.remove(mainCourse);
                System.out.println("Main Course deleted successfully!");
                return;
            }
        }
        System.out.println("Main Course not found.");
    }
    public static void deleteDrink(){
        //display drink array list then delete drink from array list drink arraylist in main

        for (Drink drink : IMR.drinkslist) {
            System.out.println(drink);
        }
        input.nextLine();
        System.out.println("Enter Drink Name to delete:");
        String drinkNameToDelete = input.nextLine();
        for (Drink drink : IMR.drinkslist) {
            if (drink.getItemName().equalsIgnoreCase(drinkNameToDelete)) {
                IMR.drinkslist.remove(drink);
                System.out.println("Drink deleted successfully!");
                return;
            }
        }
        System.out.println("Drink not found.");

    }
    public static void deleteDessert(){
        //display dessert array list then delete dessert from array list dessert in main

        for (Dessert dessert : IMR.dessertlist) {
            System.out.println(dessert);
        }
        input.nextLine();
        System.out.println("Enter Dessert Name to delete:");
        String dessertNameToDelete= input.nextLine();
        for (Dessert dessert : IMR.dessertlist) {
            if (dessert.getItemName().equalsIgnoreCase(dessertNameToDelete)) {
                IMR.dessertlist.remove(dessert);
                System.out.println("Dessert deleted successfully!");
                return;
            }
        }
        System.out.println("Dessert not found.");
    }


    public static void login(ArrayList<Chef> chefsList){
        System.out.println("      LOGIN      ");
        System.out.println("name: ");
        givenName = input.nextLine();
        System.out.println("pass: ");
        givenPass = input.nextLine();

        boolean isAuthenticated = false;

        for ( Chef chef : chefsList){
            if(chef.getUserName().equals(givenName) && chef.getPass().equals(givenPass)){
                isAuthenticated = true;
            }
        }

        if (isAuthenticated) {
            System.out.println("Login successful!");
            IMR.access = true;
        } else {
            System.out.println("Login failed. Incorrect username or password.");
        }

    }
}