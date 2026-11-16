
package IMR;


//Miral alghamdi
 public class User {
    String userName;
    String userPos; // position of the user
    private String pass; //password
    static String givenName; //used in log in funtions
    static String givenPass; //used in log in funtions

    public User(){     }

    public String getUserName() {
         return userName;
     }
    public String getUserPos() {
         return userPos;
     }
    public String getPass(){ 
         return pass; }

    public void setPass(String pass) {
        this.pass = pass;
    }
     


}
