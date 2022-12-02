package usecases;

import entities.User;

import static com.mongodb.client.model.Filters.eq;

public interface LoginDAI {
    public boolean userExists (String username);
    public void saveUser (User user);
    public Object getUserAttribute (String username, String attribute);
}
