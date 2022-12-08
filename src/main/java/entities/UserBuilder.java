package entities;

public class UserBuilder {
    private User user;

    public UserBuilder () {
        this.user = new User();
    }

    public UserBuilder firstName (String name) {
        user.setFirstName(name);
        return this;
    }

    public UserBuilder lastName (String name) {
        user.setLastName(name);
        return this;
    }

    public UserBuilder username (String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder password (String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder budget (Budget budget) {
        user.setBudget(budget);
        return this;
    }

    public UserBuilder budget () {
//        Default budget is set to 1000 if user is not initialized with a budget
        user.setBudget(new Budget(1000));
        return this;
    }

    public UserBuilder pastOrders (PastOrders pastOrders) {
        user.setPastOrders(pastOrders);
        return this;
    }

    public UserBuilder pastOrders () {
//        Default PastOrders object is given to User if it is not initialized with one
        user.setPastOrders(new PastOrders());
        return this;
    }

    public User done () {
        return this.user;
    }

}
