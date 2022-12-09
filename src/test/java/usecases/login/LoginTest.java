// Swami Shriji
package usecases.login;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    /**
     * Test checks if user and password is correct
     */
    @Test
    public void userLoggedIn(){
        Login login = new Login();
        assertEquals(2, login.loginCheck("aryangoel24", "goelaryan25"));
    }

    /**
     * Test checks if user and password is correct. This test checks what happens when password is incorrect
     */
    @Test
    public void userLogInPasswordIncorrect(){
        Login login = new Login();
        assertEquals(1, login.loginCheck("aryangoel24", "goelaryan2"));
    }

    /**
     * Test checks if user and password is correct. This tests checks that user doesn't exist
     */
    @Test
    public void userLogInUserDoesNotExist(){
        Login login = new Login();
        assertEquals(0, login.loginCheck("aryangoel", "goelaryan25"));
    }
}