// Swami Shriji
package usecases.login;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class LogicCodeTest {
    /**
     * Tests that the inputted password is extremely weak password and matches no strong password criteria such as
     * uppercase, digit and special character
     */
    @Test
    public void extremelyWeakPassword(){
        LogicCode signUp = new LogicCode();
        assertEquals(false, signUp.strongPassword("pass"));
    }

    /**
     * Tests that the inputted password is a weak password and matches only has an uppercase
     */
    @Test
    public void weakPasswordHasUpper(){
        LogicCode signUp = new LogicCode();
        assertEquals(false, signUp.strongPassword("Pass"));
    }

    /**
     * Tests that the inputted password is a weak password and matches has an uppercase, and a digit
     */
    @Test
    public void weakPasswordHasUpperDig(){
        LogicCode signUp = new LogicCode();
        assertEquals(false, signUp.strongPassword("Pass123"));
    }

    /**
     * Tests that the inputted password is a weak password and matches has an uppercase, a digit, and a special
     * character
     */
    @Test
    public void weakPasswordHasUpperDigSpecChar(){
        LogicCode signUp = new LogicCode();
        assertEquals(false, signUp.strongPassword("Pass_123"));
    }

    /**
     * Tests that the inputted password is a strong password with uppercase, digit, special character and 10 or more
     * characters
     */
    @Test
    public void strongPassword(){
        LogicCode signUp = new LogicCode();
        assertEquals(true, signUp.strongPassword("Password_123"));
    }

    /**
     * Tests the samePassword method by returning false for two different passwords
     */
    @Test
    public void differentPassword(){
        LogicCode signUp = new LogicCode();
        assertEquals(false, signUp.samePassword("Password_123", "Password_1234"));
    }

    /**
     * Tests the samePassword method by returning true for same passwords
     */
    @Test
    public void samePassword(){
        LogicCode signUp = new LogicCode();
        assertEquals(true, signUp.samePassword("Password_123", "Password_123"));
    }

    /**
     * Tests checks if user can sign up. This case returns 0 as the user already exits in database
     */
    @Test
    public void userExistsResult(){
        LogicCode signUp = new LogicCode();
        assertEquals(0, signUp.signUpCheck("aryangoel24", "Password_123", "Password_123"));
    }

    /**
     * Tests checks if user can sign up. This case returns 1 since samePassword is false
     */
    @Test
    public void differentPasswordResult(){
        LogicCode signUp = new LogicCode();
        assertEquals(1, signUp.signUpCheck("vandanpat123", "Password_123", "Password_132"));
    }

    /**
     * Tests checks if user can sign up. This case returns 2 since strongPassword is false
     */
    @Test
    public void weakPasswordResult(){
        LogicCode signUp = new LogicCode();
        assertEquals(2, signUp.signUpCheck("vandanpat123", "Password", "Password"));
    }

    /**
     * Tests checks if user can sign up. This case returns 3 and allows user to signup
     */
    @Test
    public void signUpResult(){
        LogicCode signUp = new LogicCode();
        assertEquals(3, signUp.signUpCheck("vandanpat123", "Password_123", "Password_123"));
    }
}