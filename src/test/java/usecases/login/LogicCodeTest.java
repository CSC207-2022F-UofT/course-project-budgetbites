// Swami Shriji
package usecases.login;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class LogicCodeTest {
    @Test
    public void extremelyWeakPassword(){
        LogicCode signUp = new LogicCode();
        assertEquals(false, signUp.strongPassword("pass"));
    }

    @Test
    public void weakPasswordHasUpper(){
        LogicCode signUp = new LogicCode();
        assertEquals(false, signUp.strongPassword("Pass"));
    }

    @Test
    public void weakPasswordHasUpperDig(){
        LogicCode signUp = new LogicCode();
        assertEquals(false, signUp.strongPassword("Pass123"));
    }

    @Test
    public void weakPasswordHasUpperDigSpecChar(){
        LogicCode signUp = new LogicCode();
        assertEquals(false, signUp.strongPassword("Pass_123"));
    }

    @Test
    public void strongPassword(){
        LogicCode signUp = new LogicCode();
        assertEquals(true, signUp.strongPassword("Password_123"));
    }

    @Test
    public void differentPassword(){
        LogicCode signUp = new LogicCode();
        assertEquals(false, signUp.samePassword("Password_123", "Password_1234"));
    }

    @Test
    public void samePassword(){
        LogicCode signUp = new LogicCode();
        assertEquals(true, signUp.samePassword("Password_123", "Password_123"));
    }

    @Test
    public void userExistsResult(){
        LogicCode signUp = new LogicCode();
        assertEquals(0, signUp.signUpCheck("aryangoel24", "Password_123", "Password_123"));
    }

    @Test
    public void differentPasswordResult(){
        LogicCode signUp = new LogicCode();
        assertEquals(1, signUp.signUpCheck("vandanpat123", "Password_123", "Password_132"));
    }

    @Test
    public void weakPasswordResult(){
        LogicCode signUp = new LogicCode();
        assertEquals(2, signUp.signUpCheck("vandanpat123", "Password", "Password"));
    }

    @Test
    public void signUpResult(){
        LogicCode signUp = new LogicCode();
        assertEquals(3, signUp.signUpCheck("vandanpat123", "Password_123", "Password_123"));
    }
}