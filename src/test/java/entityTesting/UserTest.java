package entityTesting;

import entities.Budget;
import entities.User;
import entities.PastOrders;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UserTest {

    // Test cases for the getter functionalities of the user Entity


    //Test case for the getFirstName getter function
    @Test
    public void getFirstNameTest() {
        User newUser = new User("Darpan", "Mishra", "darmish", "pass",
                new PastOrders(), new Budget());
        String firstName = "Darpan";
        Assertions.assertEquals(firstName, newUser.getFirstName());
    }

    // Test case for the getLastName getter funciton
    @Test
    public void getLastNameTest() {
        User newUser = new User("Darpan", "Mishra", "darmish", "pass",
                new PastOrders(), new Budget());
        String lastName = "Mishra";
        Assertions.assertEquals(lastName, newUser.getLastName());
    }


    // Test for the getUsername getter function
    @Test
    public void getUsernameTest() {
        User newUser = new User("Darpan", "Mishra", "darmish", "pass",
                new PastOrders(), new Budget());
        String username = "darmish";
        Assertions.assertEquals(username, newUser.getUsername());
    }

    //Test for the getPassword getter function
    @Test
    public void getPasswordTest() {
        User newUser = new User("Darpan", "Mishra", "darmish", "pass",
                new PastOrders(), new Budget());
        String password = "pass";
        Assertions.assertEquals(password, newUser.getPassword());
    }

    // Test case for the getPastOrders getter function
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

    // Test case for the getBudget getter function
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
    //
}
