
package IMR;

//Miral
import java.util.ArrayList;
import java.util.Scanner;

public class Order{

    static String code;
    static char type;
    static int num;
    static String elemNameCopy;
    static String elemClassificationCopy;
    static double elemPriceCopy;
    static int pos=0;//helps determine the number of orders
    static Scanner input = new Scanner(System.in);
    
    public static void orderFood(){

        IMR.displayFoodMenu();

        System.out.println("PLEASE SELECT LETTER AND NUMBER FROM FOOD MENU or e to exit");
        do {

            code = input.nextLine();
            if(code!=null && code.length()>=2 && code.charAt(1)>48 && code.charAt(1)<=57 ) {
                code = code.toUpperCase();
                type = code.charAt(0);
                num = Character.getNumericValue(code.charAt(1));
                
            }else if(code.equals("e")){
                type='E';
                code=code.toUpperCase();
            }
            else{
                type='x';
            }

            switch (type) {
                case 'S':
                    if(num-1<IMR.saladlist.size()) {
                        //find the food
                        System.out.println(IMR.saladlist.get(num - 1).getItemName());
                        elemNameCopy = IMR.saladlist.get(num - 1).getItemName();
                        elemClassificationCopy = IMR.saladlist.get(num - 1).getClassification();
                        elemPriceCopy = IMR.saladlist.get(num - 1).getPrice();
                        IMR.orderlist.add(new MenuItems(elemNameCopy, elemClassificationCopy, elemPriceCopy));
                        System.out.println("salad added!");
                        break;
                    }else{
                        System.out.println("this code doesnt exist in the menu, try again");
                    }
                    break;
                case 'M':
                    if(num-1<IMR.maincourselist.size()) {
                        System.out.println(IMR.maincourselist.get(num - 1).getItemName());
                        elemNameCopy = IMR.maincourselist.get(num - 1).getItemName();
                        elemClassificationCopy = IMR.maincourselist.get(num - 1).getClassification();
                        elemPriceCopy = IMR.maincourselist.get(num - 1).getPrice();
                        IMR.orderlist.add(new MenuItems(elemNameCopy, elemClassificationCopy, elemPriceCopy));
                        System.out.println("main course added!");
                        break;
                    }else{
                        System.out.println("this code doesnt exist in the menu, try again");
                    }
                    break;
                case 'D':
                    if(num-1<IMR.drinkslist.size()) {
                        System.out.println(IMR.drinkslist.get(num - 1).getItemName());
                        elemNameCopy = IMR.drinkslist.get(num - 1).getItemName();
                        elemClassificationCopy = IMR.drinkslist.get(num - 1).getClassification();
                        elemPriceCopy = IMR.drinkslist.get(num - 1).getPrice();
                        IMR.orderlist.add(new MenuItems(elemNameCopy, elemClassificationCopy, elemPriceCopy));
                        System.out.println("drink added!");
                        break;
                    }else{
                        System.out.println("this code doesnt exist in the menu, try again");
                    }
                    break;
                case 'Z':
                    if(num-1<IMR.dessertlist.size()){
                        System.out.println("z");
                        System.out.println(IMR.dessertlist.get(num - 1).getItemName());
                        elemNameCopy = IMR.dessertlist.get(num - 1).getItemName();
                        elemClassificationCopy = IMR.dessertlist.get(num - 1).getClassification();
                        elemPriceCopy = IMR.dessertlist.get(num - 1).getPrice();
                        IMR.orderlist.add(new MenuItems(elemNameCopy, elemClassificationCopy, elemPriceCopy));
                        System.out.println("dessert added!");
                        break;
                    }else{
                        System.out.println("this code doesnt exist in the menu, try again");
                    }
                    break;
                case 'E':
                    if(IMR.orderlist.isEmpty()){
                        System.out.println("empty");
                    }else{
                        System.out.println("Your order has been saved!");
                        addToAllOrdersList();
                    }
                    break;
                default:
                    System.out.println("Wrong entry!! try again");
                    break;
            }
        }while(type!='E');

    }

    public static void addToAllOrdersList() {

        IMR.allOrders[pos] = new ArrayList<>();

        IMR.allOrders[pos].addAll(IMR.orderlist);
        IMR.orderlist.clear();
        pos++;
    }

    public static ArrayList<MenuItems>[] deleteFromAllOrdersList() {
        System.out.println("insert number of order to delete: ");
        int indexToRemove = input.nextInt();
        indexToRemove--;

        ArrayList<MenuItems>[] newArray = new ArrayList[IMR.allOrders.length - 1];
        System.arraycopy(IMR.allOrders, 0, newArray, 0, indexToRemove);
        System.arraycopy(IMR.allOrders, indexToRemove + 1, newArray, indexToRemove, IMR.allOrders.length - indexToRemove - 1);

        pos--; //so we don't print out the non-existent order, it will avoid out of bound error

        return newArray;
    }

}
