package entityTesting;

import entities.Budget;
import entities.User;
import entities.PastOrders;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UserTest {

    /**
     * Tests getFirstName getter function to see if correct First Name is called and outputted
     */
    @Test
    public void getFirstNameTest() {
        User newUser = new User("Darpan", "Mishra", "darmish", "pass",
                new PastOrders(), new Budget());
        String firstName = "Darpan";
        Assertions.assertEquals(firstName, newUser.getFirstName());
    }

    /**
     * Tests getLastName getter function to see if correct Last Name is called and outputted
     */
    @Test
    public void getLastNameTest() {
        User newUser = new User("Darpan", "Mishra", "darmish", "pass",
                new PastOrders(), new Budget());
        String lastName = "Mishra";
        Assertions.assertEquals(lastName, newUser.getLastName());
    }

    /**
     * Tests getUsername getter function to see if correct Username is called and outputted
     */
    @Test
    public void getUsernameTest() {
        User newUser = new User("Darpan", "Mishra", "darmish", "pass",
                new PastOrders(), new Budget());
        String username = "darmish";
        Assertions.assertEquals(username, newUser.getUsername());
    }

    /**
     * Tests getPassowrd getter function to see if correct password is called and outputted
     */
    @Test
    public void getPasswordTest() {
        User newUser = new User("Darpan", "Mishra", "darmish", "pass",
                new PastOrders(), new Budget());
        String password = "pass";
        Assertions.assertEquals(password, newUser.getPassword());
    }

    /**
     * Tests getPastOrders getter function to see if correct past orders is called and outputted
     */
    @Test
    public void getPastOrdersTest() {
        PastOrders pastOrders;
        pastOrders = new PastOrders();

        Budget budget;
        budget = new Budget();

        User newUser = new User("Darpan", "Mishra", "darmish", "pass",
                pastOrders, budget);
        Assertions.assertEquals(pastOrders, newUser.getPastOrders());
    }

    /**
     * Tests getBudget getter function to see if correct budget is called and outputted
     */
    @Test
    public void getBudgetTest() {
        PastOrders pastOrders;
        pastOrders = new PastOrders();

        Budget budget;
        budget = new Budget();

        User newUser = new User("Darpan", "Mishra", "darmish", "pass",
                pastOrders, budget);
        Assertions.assertEquals(budget, newUser.getBudget());
    }
}
