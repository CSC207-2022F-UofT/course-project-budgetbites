//Swami Shriji
package usecases.login;
import usecases.LoginDAI;
import gateways.MainMongoDB;

public class Login {
    private final LoginDAI loginDAI = new MainMongoDB();
    public int loginCheck(String user, String password){
        if (loginDAI.userExists(user)){
            String correctPassword = (String) loginDAI.getUserAttribute(user, "password");
            if (password.equals(correctPassword)){
                return 2;
            } else {
                // incorrect Password
                return 1;
            }
        } else{
            // user doesn't exist
            return 0;
        }
    }

//    public static void main(String[] args) {
//        Login.loginCheck("aryangoel24", "goelaryan25");
//    }
}
