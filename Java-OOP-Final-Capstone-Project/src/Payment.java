
package IMR;

//miral alghamdi
import java.util.ArrayList;
import java.util.Scanner;

public class Payment{
    //variables
    static final double VAT = 0.15;
    static double totalPrice = 0;
    static char paymentType;
    static String paymentMethod;
    static boolean accuratePay=false;
    static Scanner input = new Scanner(System.in);

    //methods
    public static void bill(ArrayList <MenuItems> orderList){
        totalPrice=0;
        do {
            System.out.println("SELECT PAYMENT METHOD:      1-cash         2-credit card        3-ApplePay");
            paymentType = input.next().charAt(0);
            switch (paymentType) {
                case '1':{
                    paymentMethod="Payed with cash";
                    accuratePay = true;
                    break;}
                case '2':{
                    paymentMethod="Payed with credit card";
                    accuratePay = true;
                    break;}
                case '3':{
                    paymentMethod="Payed with ApplePay";
                    accuratePay = true;
                    break;}
                default:{
                    System.out.println("wrong entry!! try again");
                    break;}
            }
        }while(accuratePay!=true);

        //calculating bill
        for (MenuItems thisOrder:orderList){
            totalPrice += thisOrder.getPrice();
        }

        //bill
        System.out.println("---------------------BILL----------------------");
        for (MenuItems dessert : orderList) {
            System.out.printf("%-20s %-15s %.2f SR\n", dessert.getItemName(), dessert.getClassification(), dessert.getPrice());
        }
        System.out.println("\n\nPAYMENT METHOD:       " + paymentMethod);
        System.out.println("total before adding VAT: " + totalPrice + " SR");
        totalPrice = totalPrice + (totalPrice*VAT);
        System.out.println("total after adding VAT:   " + totalPrice + " SR" );

        System.out.println("-----------------------------------------------");

    }

    public static int selectAnOrderToPay(){
        int index=0;

        IMR.displayAllOrders();

        System.out.println("Select the order number to pay:");
        
        index = input.nextInt();

        return index-1;
    }

}

