package entities;

public class User {
    public String firstName;
    public String lastName;
    public String username;
    public String password;
    public Budget budget;
    public PastOrders pastOrders;

    public ItemCart itemCart;
    
    public User () {
    }

    public User (String firstName, String lastName, String username, String password, PastOrders pastOrders, Budget budget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.budget = budget;
        this.pastOrders = pastOrders;
//        this.ItemCart = new ItemCart();
    }

    public String getFirstName () {
        return this.firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public String getUsername () {
        return username;
    }

    public String getPassword () {
        return password;
    }

    public PastOrders getPastOrders () {
        return pastOrders;
    }

    public Budget getBudget () {return budget;}


}
