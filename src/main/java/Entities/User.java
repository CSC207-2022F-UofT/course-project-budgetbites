package Entities;

import Use_Cases.itemCart;
import Use_Cases.pastOrders;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Budget budget;
    private pastOrders pastOrders;
    private itemCart itemCart;

    public User (String firstName, String lastName, String username, String password, Budget budget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.budget = budget;
        this.pastOrders = new pastOrders();
        this.itemCart = new itemCart();
    }

    public User (String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.budget = new Budget();
        this.pastOrders = new pastOrders();
        this.itemCart = new itemCart();
    }
}
