package IMR;

//main page
//Miral alghamdi

import java.util.ArrayList;
import java.util.Scanner;

class IMR{


    //variables
    static char choice; //for switch
    static boolean access = false;  //for login
    static int i=0; //for counter
    static Scanner input = new Scanner(System.in);
    static ArrayList <Manager> managerslist = new ArrayList <>(); //list for Managers
    static ArrayList <Chef> chefslist = new ArrayList <>(); //list for chefs
    static ArrayList <Waiter> waiterslist = new ArrayList <>(); //list for waiters
    static ArrayList <Reservation> tableslist = new ArrayList <>(); //list for tables
    static ArrayList <Salad> saladlist = new ArrayList <>(); //list for salad
    static ArrayList <MainCourse> maincourselist = new ArrayList <>(); //list for main courses
    static ArrayList <Drink> drinkslist = new ArrayList <>(); //list for drinks
    static ArrayList <Dessert> dessertlist = new ArrayList <>(); //list for desserts
    static ArrayList <MenuItems> orderlist = new ArrayList<>();
    static ArrayList <MenuItems> [] allOrders = new ArrayList[10];


    //methods

    //main menu
    public static void Menu(){
        System.out.println("------------------------------------------------");
        System.out.println("---------MAIN MENU---------");
        System.out.println("choose the service: ");
        System.out.println("1- show menu");
        System.out.println("2- manager ");
        System.out.println("3- chef");
        System.out.println("4- waiter");
        System.out.println("5- reservations");
        System.out.println("6- exist the program");
        System.out.println("---------------------------");
    }
    //display menu for each employee
    public static void Menu(int i){
        switch(i){
            case 1:
                //manager menu
                System.out.println("---------------------------");
                System.out.println("-------MANAGER MENU--------");
                System.out.println("choose the service: ");
                System.out.println("1- add employee");
                System.out.println("2- delete employee");
                System.out.println("3- display Chefs list");
                System.out.println("4- display Waiter list");
                System.out.println("5- back to main menu");
                System.out.println("---------------------------");
                break;
            case 2:
                //chef menu
                System.out.println("---------------------------");
                System.out.println("---------CHEF MENU---------");
                System.out.println("choose the service: ");
                System.out.println("1- add item to menu");
                System.out.println("2- delete item in menu");
                System.out.println("3- back to main menu");
                System.out.println("---------------------------");
                break;
            case 3:
                //waiter menu
                System.out.println("---------------------------");
                System.out.println("--------WAITER MENU--------");
                System.out.println("choose the service: ");
                System.out.println("1- order");
                System.out.println("2- delete order");
                System.out.println("3- pay for an order");
                System.out.println("4- show all orders list");
                System.out.println("5- back to main menu");
                System.out.println("---------------------------");
                break;
            default:
                //nothing
                break;
        }
    }
    //display food menu
    public static void displayFoodMenu(){

        int s=0,m=0,d=0,z=0;

        System.out.println("----------------------------------MENU---------------------------------- ");

        for (Salad salad : saladlist) {
            s++;
            System.out.printf("S%-2d %-20s %-15s %.2f SR\n", s, salad.getItemName(), salad.getClassification(), salad.getPrice());
        }

        for (MainCourse mainCourse : maincourselist) {
            m++;
            System.out.printf("M%-2d %-20s %-15s %.2f SR\n", m, mainCourse.getItemName(), mainCourse.getClassification(), mainCourse.getPrice());
        }

        for (Drink drink : drinkslist) {
            d++;
            System.out.printf("D%-2d %-20s %-15s %.2f SR\n", d, drink.getItemName(), drink.getClassification(), drink.getPrice());
        }

        for (Dessert dessert : dessertlist) {
            z++;
            System.out.printf("Z%-2d %-20s %-15s %.2f SR\n", z, dessert.getItemName(), dessert.getClassification(), dessert.getPrice());
        }

        System.out.println("------------------------------------------------------------------------- ");
    }

    //display all orders in the restaurant
    public static void displayAllOrders() {
        System.out.println("ALL ORDERS:");
        ArrayList <MenuItems> anOrder = new ArrayList<>();
        for (i = 0; i < Order.pos; i++) {
            anOrder = allOrders[i];

            System.out.println("Order " + (i + 1) + ":");
            for (MenuItems anItem : anOrder) {
                System.out.printf("%-20s %-15s %.2f SR\n", anItem.getItemName(), anItem.getClassification(), anItem.getPrice());
            }
            System.out.println("-------------------------");
        }
    }

    

    public static void main(String[] args) {


        //defaults
        managerslist.add(new Manager("Miral","M","111"));
        managerslist.add(new Manager("Hanoof","M","222"));

        chefslist.add(new Chef("Dana","C","333"));
        chefslist.add(new Chef("Shahad","C","444"));

        waiterslist.add(new Waiter("Walaa","W","555"));
        waiterslist.add(new Waiter("Fatimah","W","666"));
        waiterslist.add(new Waiter("Asala","W","777"));

        saladlist.add(new Salad("Ceaser Salad","Salad",20.0));
        saladlist.add(new Salad("Green Salad","Salad",18.0));

        maincourselist.add(new MainCourse("Pizza","Main Course",25.0));
        maincourselist.add(new MainCourse("Lasagna","Main Course",30.0));

        drinkslist.add(new Drink("Mojito","Drink",15.0));
        drinkslist.add(new Drink("Orange Juice","Drink",17.0));

        dessertlist.add(new Dessert("Teramisu","dessert",22.0));
        dessertlist.add(new Dessert("Lava Cake","dessert",20.0));

        //------------------------parting

        System.out.println("WELCOME TO ITALIANO RESTAURANT SYSTEM");
        //print main menu


        do {
            Menu();
            choice = input.next().charAt(0);
            
            switch (choice) {
                case '1':
                    //restaurant menu
                    displayFoodMenu();
                    break;
                case '2':
                    //login
                    Manager.login(managerslist);
                    //switch services for manager
                    if (access) {
                        do {
                            //managerMenu();
                            Menu(1);
                            System.out.println("choose the service you want");
                            choice = input.next().charAt(0);
                            switch (choice) {
                                case '1':
                                    //add emp
                                    Manager.addEmployee();
                                    break;
                                case '2':
                                    //delete emp
                                    Manager.deleteEmployee();
                                    break;
                                case '3':
                                    //display chef
                                    Manager.displayChefs();
                                    break;
                                case '4':
                                    //display waiter
                                    Manager.displayWaiters();
                                    break;
                                case '5':
                                    //exit
                                    break;
                                default:
                                    System.out.println("wrong entry!! try again");
                            }
                        } while (choice != '5');
                        access = false;
                        break;
                    } else {
                        break;
                    }
                case '3':
                    //login
                    Chef.login(chefslist);
                    if (access) {
                        //switch services for chef
                        do {
                            //chefMenu();
                            Menu(2);
                            System.out.println("choose the service");
                            choice = input.next().charAt(0);
                            switch (choice) {
                                case '1':
                                    //add food
                                    Chef.addFood();
                                    break;
                                case '2':
                                    //delete food
                                    Chef.deleteFood();
                                    break;
                                case '3':
                                    //exit
                                    break;
                                default:
                                    System.out.println("wrong entry!! try again");
                            }
                        } while (choice != '3');
                        access = false;
                        break;
                    } else {
                        break;
                    }
                case '4':
                    //login
                    Waiter.login(waiterslist);
                    //switch services for waiter
                    if (access) {
                        do {
                            //waiterMenu();
                            Menu(3);
                            System.out.println("choose the service");
                            choice = input.next().charAt(0);
                            switch (choice) {
                                case '1':
                                    //add order
                                    Order.orderFood();
                     
                                    break;
                                case '2':
                                    //delete order
                                    if (Order.pos == 0) {
                                        System.out.println("there is no order yet!");
                                    } else {
                                        displayAllOrders();
                                        allOrders = Order.deleteFromAllOrdersList();
                                        System.out.println("Orders after deleting");
                                        displayAllOrders();
                                    }
                                    break;
                                case '3':
                                    //pay for an order
                                    int orderIndex = Payment.selectAnOrderToPay();
                                    Payment.bill(allOrders[orderIndex]);
                                    break;
                                case '4':
                                    //display all orders
                                    displayAllOrders();
                                    break;
                                case '5':
                                    //exist
                                    break;
                                default:
                                    System.out.println("wrong entry!! try again");
                                    break;
                            }
                        } while (choice != '5');
                        access = false;
                    } else {
                        break;
                    }
                    break;
                case '5':
                    //reservation
                    Reservation.reservationMenu();
                    break;
                case '6':
                    //exist
                    System.out.println("goodbye have a good day! :)");
                    break;
                default:
                    //for wrong entry
                    System.out.println("wrong entry!! try again");
                    break;
            }
        } while (choice != '6');

    }
}