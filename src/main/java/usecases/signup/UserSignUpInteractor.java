package usecases.signup;
import entities.Budget;
import entities.PastOrders;
import entities.User;
import usecases.LoginDAI;
import gateways.MainMongoDB;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserSignUpInteractor {

    private final LoginDAI loginDAI = new MainMongoDB();


    /**
     * The following code checks the strength of the password by checking if the password entered meets the following criteria: uppercase, digit, special character and is 10 characters long.
     * @param password inputted by the user
     * @return boolean
     */
    public boolean strongPassword (String password){
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean specialChar = false;
        Set<Character> set = new HashSet<>(
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
        return hasUpper && hasDigit && specialChar && (password.length() >= 10);
    }

    /**
     * checks if both passwords inputted password and confirm password are the same
     * @param password inputted by the user
     * @param confirmPassword inputted by the user
     * @return boolean
     */
    public boolean samePassword(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }

    /**
     * checks if the user doesn't exist, if password and confirm password are the same, and if the password is strong.
     * @param username inputted by the user
     * @param password inputted by the user
     * @param confirmPassword inputted by the user
     * @return int
     */
    public int signUpCheck(String username, String password, String confirmPassword) {
        if (loginDAI.userExists(username)) {
            return 0;
        } else {
            if (samePassword(password, confirmPassword)) {
                if (strongPassword(password)) {
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
    /**
     * Creates the user and adds them to the database
     * @param firstName inputted by the user
     * @param lastName inputted by the user
     * @param username inputted by the user
     * @param password inputted by the user
     * @param budget inputted by the user
     */
    public void saveUser(String firstName, String lastName, String username, String password, double budget){
        User signupUser = new User(firstName, lastName, username,password, new PastOrders(), new Budget(budget));
        loginDAI.saveUser(signupUser);
    }
}
