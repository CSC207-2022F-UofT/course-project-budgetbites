//Swami Shriji
package usecases.login;
import usecases.MainMongoDB;

public class login {
    public static boolean loginCheck(String user, String password){
        if (MainMongoDB.userExists(user)){
            String correctPassword = (String) MainMongoDB.getUserAttribute(user, "password");
            if (password.equals(correctPassword)){
                return true;
            } else {
                // final submission on gui should be able to say "Incorrect Password"
                return false;
            }
        } else{
            return false;
            //System.out.println("username doesn't exist");
        }
    }

    public static void main(String[] args) {
        login.loginCheck("aryangoel24", "goelaryan25");
    }
}
