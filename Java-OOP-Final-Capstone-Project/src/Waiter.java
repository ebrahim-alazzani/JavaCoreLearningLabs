
package IMR;

//miral
import java.util.ArrayList;
import java.util.Scanner;

public class Waiter extends User{

    static Scanner input = new Scanner(System.in);

    public Waiter(String userName, String userPos, String pass){
        this.userName = userName;
        this.userPos = userPos;
        super.setPass(pass);
    }

    public static void login(ArrayList<Waiter> waitersList){
        System.out.println("      LOGIN      ");
        System.out.println("name: ");
        givenName = input.nextLine();
        System.out.println("pass: ");
        givenPass = input.nextLine();

        boolean isAuthenticated = false;

        for ( Waiter waiter : waitersList){
            if(waiter.getUserName().equals(givenName) && waiter.getPass().equals(givenPass)){
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
