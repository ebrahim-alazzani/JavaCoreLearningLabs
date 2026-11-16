
package IMR;


//Miral and Fatimah
import java.util.ArrayList;
import java.util.Scanner;
public class Manager extends User{
    
    static int choice;
    static Scanner input = new Scanner(System.in);

    public Manager(String userName, String userPos, String pass){
        this.userName = userName;
        this.userPos = userPos;
        super.setPass(pass);
    }

    public static void addEmployee(){
        System.out.println("would you like to add a chef or waiter?");
        System.out.println("1-chef          2-waiter");
        choice = input.nextInt();

        switch(choice){
            case 1:
                //add cheff
                addChef();
                break;
            case 2:
                //add waiter
                addWaiter();
                break;
            default:
                System.out.print("wrong entry");
        }
    }
    public static void deleteEmployee(){
        System.out.println("would you like to delete a chef or waiter?");
        System.out.println("1-chef          2-waiter");
        choice = input.nextInt();

        switch(choice){
            case 1:
                //delete cheff
                deleteChef();
                break;
            case 2:
                //delete waiter
                deleteWaiter();
                break;
            default:
                System.out.print("wrong entry");
        }

    }
    public static void addChef(){
        //add chef to array list
        input.nextLine();
        System.out.println("Enter Chef User Name :");
        String userName = input.nextLine();
        System.out.println("Enter Chef User Position :");
        String userPos = input.nextLine();
        System.out.println("Enter Chef User Password :");
        String pass = input.nextLine();
        Chef chef = new Chef(userName, userPos, pass);
        IMR.chefslist.add(chef);
        System.out.println("cheff added successfully!");
    }

    public static void deleteChef(){
        //delete chef from array list chef in main
        displayChefs();
        input.nextLine();
        System.out.println("Enter Chef Name To Delete :");
        String chefNameToDelete = input.nextLine();
        for (Chef chef : IMR.chefslist) {
            if (chef.getUserName().equalsIgnoreCase(chefNameToDelete)) {
                IMR.chefslist.remove(chef);
                System.out.println("cheff deleted successfully!");
                return;
            }
        }
        System.out.println("chef not found.");
    }


    public static void displayChefs(){
        //display all chefs in chef array list
        if (IMR.chefslist.isEmpty()) {
            System.out.println("No chefs available.");
            return;
        }

        System.out.println("==================================");
        System.out.println("          List of chefs          ");
        System.out.println("==================================");
        for (Chef chef : IMR.chefslist) {
            System.out.println("Chef Name: " + chef.getUserName());
            System.out.println("Chef Position: " + chef.getUserPos());
            System.out.println("------------------------");
        }
    }

    public static void addWaiter(){
        //add waiter to array list
        input.nextLine();
        System.out.println("Enter Waiter User Name :");
        String userName = input.nextLine();
        System.out.println("Enter Waiter User Position :");
        String userPos = input.nextLine();
        System.out.println("Enter Waiter User Password :");
        String pass = input.nextLine();
        Waiter waiter = new Waiter(userName, userPos, pass);
        IMR.waiterslist.add(waiter);
        System.out.println("waiter added successfully!");

    }

    public static void deleteWaiter(){
        //delete waiter from array list waiter in main
        if (IMR.waiterslist.isEmpty()) {
            System.out.println("No waiters available.");
            return;
        }

        displayWaiters();

        input.nextLine();
        System.out.println("Enter Waiter Name To Delete :");
        String waiterNameToDelete = input.nextLine();

        for (Waiter waiter : IMR.waiterslist) {
            if (waiter.getUserName().equalsIgnoreCase(waiterNameToDelete)) {
                IMR.waiterslist.remove(waiter);
                System.out.println("Waiter deleted successfully!");
                return;
            }
        }

        System.out.println("Waiter not found.");
    }

    public static void displayWaiters(){
        //display all waiters in waiter array list

        if (IMR.waiterslist.isEmpty()) {
            System.out.println("No waiters available.");
            return;
        }

        System.out.println("==================================");
        System.out.println("          List of Waiters          ");
        System.out.println("==================================");

        for (Waiter waiter : IMR.waiterslist) {
            System.out.println("Waiter Name: " + waiter.getUserName());
            System.out.println("Waiter Position: " + waiter.getUserPos());
            System.out.println("------------------------------");
        }
    }

    public static void login(ArrayList<Manager> managersList){
        System.out.println("      LOGIN      ");
        System.out.println("name: ");
        givenName = input.nextLine();
        System.out.println("pass: ");
        givenPass = input.nextLine();

        boolean isAuthenticated = false;

        for ( Manager manager : managersList){
            if(manager.getUserName().equals(givenName) && manager.getPass().equals(givenPass)){
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