
package IMR;
import java.util.Scanner;
//asala
public class Reservation {

    private int phoneNum;
    private String customerName;
    private int totalNumOfCustomer;
    private String reservationTime;
    static char choice;
    static Scanner input = new Scanner(System.in);
    

    public Reservation(int phoneNum, String reservationTime, String customerName, int totalNumOfCustomer) {
        this.phoneNum = phoneNum;
        this.reservationTime = reservationTime;
        this.customerName = customerName;
        this.totalNumOfCustomer = totalNumOfCustomer;
    }

    public static void reservationMenu() {
        System.out.println("would you like to delete or add reservation?");
        System.out.println("1-Add Reservation          \n2-Delete Reservation       \n3-Display Reservation");
        choice = input.next().charAt(0);

        switch (choice) {
            case '1':{
                if (IMR.tableslist.size() < 100 || IMR.tableslist.size() == 100) {
                    addReservation();
                } else {
                    System.out.println("No reservation is available!");
                }
                break;}
            case '2':{
                deleteReservation();
                break;}
            case '3':{
                displayTablesList();
                break;}
            default:{
                System.out.println("wrong entry");
                break;}
        }
    }


    public static void addReservation() {
        // Add the table info to the array list "tables" in main
        input.nextLine();

        System.out.println("Enter phone number: ");
        int phoneNum = input.nextInt();

        input.nextLine();

        System.out.println("Enter reservation time: ");
        String reservationTime = input.nextLine();

        System.out.println("Enter customer name: ");
        String customerName = input.nextLine();

        System.out.println("Enter total number of Customers: ");
        int totalNumOfCustomer = input.nextInt();

        Reservation reservation = new Reservation(phoneNum, reservationTime, customerName, totalNumOfCustomer);
        IMR.tableslist.add(reservation);
        System.out.println("Reservation added");
    }


    public static void deleteReservation() {
        //delete the table info from the array list "tables" in main

        input.nextLine();
        System.out.println("Enter phone number to delete the reservation:");
        int phoneNumToDelete = input.nextInt();

        input.nextLine();


        for (Reservation reservation : IMR.tableslist) {
            if (reservation.phoneNum == phoneNumToDelete) {
                IMR.tableslist.remove(reservation);
                System.out.println("Reservation deleted successfully!");
                return;
            }
        }

        System.out.println("Reservation not found.");

    }

    public static void displayTablesList() {
        //display all tables
        if (IMR.tableslist.isEmpty()) {
            System.out.println("No reservations available.");
            return;
        }

        System.out.println("List of Reservations:");
        for (Reservation reservation : IMR.tableslist) {
            System.out.println(reservation);
            System.out.println("------------------------");
        }

    }

    @Override
    public String toString() {
        return "Reservation Details: "+"\nCustomer Name: " + customerName +"\nPhone Number: " + phoneNum +"\nReservation Time: " + reservationTime;
    }
}

