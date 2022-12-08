package entityTesting;

import entities.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class UserBuilderTest {
    /**
     * Creates a UserBuilder object
     */
    UserBuilder userBuilder = new UserBuilder();

    /**
     * Tests if accurate first name, last name, username, password, budgets and past order is gotten when budget and
     * past orders are not entered
     */
    @Test
    public void UserBuilder_NoBudget_NoPastOrders() {
        User user = userBuilder.firstName("Aryan").lastName("Goel").username("aryangoel24").password("goelaryan25")
                .budget().pastOrders().done();

        Assertions.assertEquals(user.getFirstName(), "Aryan");
        Assertions.assertEquals(user.getLastName(), "Goel");
        Assertions.assertEquals(user.getUsername(), "aryangoel24");
        Assertions.assertEquals(user.getPassword(), "goelaryan25");
        // Default budget is set to 1000
        Assertions.assertEquals(user.getBudget().getInitialBudget(), 1000);
        Assertions.assertEquals(user.getBudget().getCurrentBudget(), 1000);
        // Since no PastOrders object was passed, the pastordersmap should be empty
        Assertions.assertTrue(user.getPastOrders().getPastOrdersMap().isEmpty());
    }

    /**
     * Tests if accurate first name, last name, username, password, budgets and past order is gotten
     */
    @Test
    public void UserBuilder () {
        Budget budget = new Budget(100);
        PastOrders pastOrders = new PastOrders();
        User user = userBuilder.firstName("Aryan").lastName("Goel").username("aryangoel24").password("goelaryan25")
                .budget(budget).pastOrders(pastOrders).done();

        Assertions.assertEquals(user.getFirstName(), "Aryan");
        Assertions.assertEquals(user.getLastName(), "Goel");
        Assertions.assertEquals(user.getUsername(), "aryangoel24");
        Assertions.assertEquals(user.getPassword(), "goelaryan25");
        Assertions.assertEquals(user.getBudget(), budget);
        Assertions.assertEquals(user.getPastOrders(), pastOrders);
    }
}