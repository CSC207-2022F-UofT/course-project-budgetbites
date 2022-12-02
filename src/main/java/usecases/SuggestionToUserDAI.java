package usecases;

import entities.PastOrders;
import entities.User;

import static com.mongodb.client.model.Filters.eq;

public interface SuggestionToUserDAI {
    public PastOrders findPastOrders (String username);
    public Object getUserAttribute (String username, String attribute);
}
