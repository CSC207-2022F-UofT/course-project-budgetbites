import entities.FoodItem;
import entities.PastOrders;
import usecases.MainMongoDB;
import org.junit.Test;

import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;


public class suggestiontousertest {
    private final PastOrders testPastOrder = MainMongoDB.findPastOrders("aryangoel24");

    @Test(timeout = 500)
    public void te() {
        LinkedHashMap<FoodItem, Integer> populatedSortedMap = new LinkedHashMap<>();
//        FoodItem food1 = new FoodItem("food1", 10.00);
//        FoodItem food2 = new FoodItem("food2", 13.50);
//        FoodItem food3 = new FoodItem("food3", 5.00);
//        FoodItem food4 = new FoodItem("food4", 8.00);
//        populatedSortedMap.put(food1, 4);
//        populatedSortedMap.put(food2, 4);
//        populatedSortedMap.put(food3, 3);
//        populatedSortedMap.put(food4, 2);
    }
}
