//Swami Shriji
package usecases.login;
import usecases.LoginDAI;
import gateways.MainMongoDB;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LogicCode {

    private final LoginDAI loginDAI = new MainMongoDB();
    /**
     * The following code checks if the methods below are able to execute and save the user inputs.
     * @param args
     */
    public void main(String[] args) {
        System.out.println(signUpCheck("vandanpatel", "Vp@1232848327498473", "Vp@1232848327498473"));
    }

    /**
     * The following code checks the strength of the password by checking if the password entered meets the following criteria: uppercase, digit, special character and is 10 characters long.
     * @param password
     * @return boolean
     */
    public boolean strongPassword (String password){
        int n = password.length();
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean specialChar = false;
        Set<Character> set = new HashSet<Character>(
                Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+', '.', '_'));
        for (char i : password.toCharArray())
        {
            if (Character.isUpperCase(i))
                hasUpper = true;
            if (Character.isDigit(i))
                hasDigit = true;
            if (set.contains(i))
                specialChar = true;
        }

        // checks the strength of the password by if the password has an uppercase, digit, special character and password length
        if (hasUpper && hasDigit && specialChar && (password.length() >= 10)) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * checks if both passwords inputted password and confirm password are the same
     * @param password
     * @param confirmPassword
     * @return boolean
     */
    public boolean samePassword(String password, String confirmPassword){
        if (password.equals(confirmPassword)){
            return true;
        } else{
            return false;
        }
    }

    /**
     * signs up and saves the user data if the user doesn't exist, password and confirm password are the same and the password is strong.
     * @param username
     * @param password
     * @param confirmPassword
     * @return boolean
     */
    public int signUpCheck(String username, String password, String confirmPassword) {
        if (loginDAI.userExists(username)) {
            return 0;
        } else {
            if (samePassword(password, confirmPassword)) {
                if (strongPassword(password)) {
//                    PastOrders p1 = new PastOrders();
//                    Budget b1 = new Budget(initialBudget);
//                    User user = new User(firstName, lastName, username, password, p1, b1);
//                    loginDAI.saveUser(user);
                    // all requirements match for user to save inputted data
                    return 3;
                } else {
                    // tell the user that this password isn't strong
                    return 2;
                }
            } else {
                // tell the user that password and confirm password are the same
                return 1;
            }
        }
    }
}
