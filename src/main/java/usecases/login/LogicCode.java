//Swami Shriji
package usecases.login;
import entities.Budget;
import entities.PastOrders;
import entities.User;
import usecases.MainMongoDB;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LogicCode {
    /**
     * The following code checks if the methods below are able to execute and save the user inputs.
     * @param args
     */
    public static void main(String[] args) {
        LogicCode.signUpCheck("vandanpatel", "Vp@1232848327498473", "Vp@1232848327498473", 50.00, "Vandan", "Patel");
    }

    /**
     * The following code checks the strength of the password by checking if the password entered meets the following criteria: uppercase, digit, special character and is 10 characters long.
     * @param password
     * @return boolean
     */
    public static boolean strongPassword (String password){
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
    public static boolean samePassword(String password, String confirmPassword){
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
     * @param initialBudget
     * @param firstName
     * @param lastName
     * @return boolean
     */
    public static boolean signUpCheck(String username, String password, String confirmPassword,
                                      double initialBudget, String firstName, String lastName) {
        // help with MainMongoDB
        if (MainMongoDB.userExists(username)) {
            return false;
        } else {
            if (samePassword(password, confirmPassword)) {
                if (strongPassword(password)) {
                    PastOrders p1 = new PastOrders();
                    Budget b1 = new Budget(initialBudget);
                    User user = new User(firstName, lastName, username, password, p1, b1);
//                    commented out this code for functionality purposes
//                    MainMongoDB.saveUser(user);
                    return true;
                } else {
                    // final code should be able to tell the user that this password isn't strong
                    return false;
                }
            } else {
                // final code should be able to tell the user that password and confirm password are the same
                return false;
            }
        }
    }


//    	Scanner begin = new Scanner(System.in);
//    	System.out.println("Are a new user? (Y/N):");
//    	char entry = begin.next().charAt(0);
//
//    	Scanner fName = new Scanner(System.in);
//    	Scanner lName = new Scanner(System.in);
//    	Scanner userName = new Scanner(System.in);
//    	Scanner pword = new Scanner(System.in);
//    	Scanner cpword = new Scanner(System.in);
//    	Scanner bget = new Scanner(System.in);
//
//    	String firstName;
//    	String lastName;
//    	String utorid;
//    	String password;
//    	String confirmPassword;
//    	double budget;
//
////
////    	String[][] users = new String[5][5];
//
//
//    	if (entry == 'Y' || entry == 'y') {
//
//    		System.out.println("Enter first name");
//    		firstName = fName.nextLine();
//
//    		System.out.println("Enter last name");
//            lastName = lName.nextLine();
//
//            System.out.println("Enter UTORid");
//            utorid = userName.nextLine();
//
//          //check if utorid is correct
//            int utoirdLen = utorid.length();
//            if (utoirdLen == 8){
//                System.out.println("Valid UTORid");
//            } else{
//                System.out.println("Incorrect UTORid");
//            }
//
//            System.out.println("Enter password");
//            password = pword.nextLine();
//
//            System.out.println("Enter confirmation password");
//            confirmPassword = cpword.nextLine();
//
//          //check if password and confirmation password is the same and strong
//            if (password.equals(confirmPassword)){
//                System.out.println("true");
//                // check if password has uppercase special character and number
//            } else {
//                System.out.println("False");
//            }
//
//            System.out.println("Enter budget (optional)");
//            budget = bget.nextDouble();
//
//
//    	} else if (entry == 'N' || entry == 'n'){
//    		System.out.println("Enter UTORid");
//            utorid = userName.nextLine();
//
//          //check if utorid is correct
//            int utoirdLen = utorid.length();
//            if (utoirdLen == 8){
//                System.out.println("Valid UTORid");
//            } else{
//                System.out.println("Incorrect UTORid");
//            }
//
//            System.out.println("Enter password");
//            password = pword.nextLine();
//    	}
//       }

}
