package entities.designpatterns;

import entities.Budget;
import entities.PastOrders;
import entities.User;

public class UserBuilder {
    private final User user;

    /**
     * UserBuilder Constructor: Instantiates a new user as the user instance attribute
     */
    public UserBuilder () {
        this.user = new User();
    }

    /**
     * Sets first name of user to the name provided
     * @param name: The desired first name of the user
     * @return returns this (a UserBuilder object)
     */
    public UserBuilder firstName (String name) {
        user.setFirstName(name);
        return this;
    }

    /**
     * Sets last name of user to the name provided
     * @param name: The desired last name of the user
     * @return returns this (a UserBuilder object)
     */
    public UserBuilder lastName (String name) {
        user.setLastName(name);
        return this;
    }

    /**
     * Sets username of user to the username provided
     * @param username: The desired username of the user
     * @return returns this (a UserBuilder object)
     */
    public UserBuilder username (String username) {
        user.setUsername(username);
        return this;
    }

    /**
     * Sets password of user to the password provided
     * @param password: The desired password of the user
     * @return returns this (a UserBuilder object)
     */
    public UserBuilder password (String password) {
        user.setPassword(password);
        return this;
    }

    /**
     * Sets budget of user to the budget object provided
     * @param budget: The desired budget object of the user
     * @return returns this (a UserBuilder object)
     */
    public UserBuilder budget (Budget budget) {
        user.setBudget(budget);
        return this;
    }

    /**
     * Sets budget of user to a default budget object with initial and current budget 1000
     * @return returns this (a UserBuilder object)
     */
    public UserBuilder budget () {
//        Default budget is set to 1000 if user is not initialized with a budget
        user.setBudget(new Budget(1000));
        return this;
    }

    /**
     * Sets pastorders of user to the PastOrders object provided
     * @param pastOrders: The desired PastOrders object of the user
     * @return returns this (a UserBuilder object)
     */
    public UserBuilder pastOrders (PastOrders pastOrders) {
        user.setPastOrders(pastOrders);
        return this;
    }

    /**
     * Sets PastOrders of user to an empty PastOrders object
     * @return returns this (a UserBuilder object)
     */
    public UserBuilder pastOrders () {
//        Default PastOrders object is given to User if it is not initialized with one
        user.setPastOrders(new PastOrders());
        return this;
    }

    /**
     * @return returns the user
     */
    public User done () {
        return this.user;
    }

}
