import entities.FoodItem;
import entities.Order;
import entities.PastOrders;
import gateways.MainMongoDB;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import usecases.SuggestionToUserDAI;
import usecases.foodsuggestions.SuggestionToUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SuggestionToUserTest {

    private final SuggestionToUserDAI suggestionToUserDatabase = new MainMongoDB();
    private SuggestionToUser suggestionToUserInteractor = new SuggestionToUser(suggestionToUserDatabase);

    private String testUser = "aryangoel24";
    private String testUser2 = "darpanmi";

    //    @BeforeEach
//    public void setUp() {
//        FoodSuggestionsController foodSuggestionsController = new FoodSuggestionsController(suggestionToUserInteractor);
//        userSuggestionFrame foodSuggestionViewer = new userSuggestionFrame(foodSuggestionsController);
//        IUserSuggestion.Presenter foodSuggestionsPresenter = new FoodSuggestionPresenter(foodSuggestionViewer);
//        suggestionToUserDatabase = new MainMongoDB();
//        suggestionToUserInteractor = new SuggestionToUser(suggestionToUserDatabase, foodSuggestionsPresenter);
//    }
    @Test
    public void testSortingHashMapMongo() {
        PastOrders testOrder = suggestionToUserDatabase.findPastOrders(testUser);
        assert testOrder != null;
        final HashMap<String, Order> pastOrdersMap = testOrder.getPastOrdersMap();
        LinkedHashMap<FoodItem, Integer> testSortingHashMap = suggestionToUserInteractor.sortingHashMap(suggestionToUserInteractor.itemCount(testOrder, pastOrdersMap));
        List<Integer> expectedValues = new ArrayList<Integer>(testSortingHashMap.values());
        List<Integer> expectedSortedListValue = new ArrayList<>();
        expectedSortedListValue.add(3);
        expectedSortedListValue.add(2);
        expectedSortedListValue.add(1);
        expectedSortedListValue.add(1);
        Assertions.assertEquals(expectedValues, expectedSortedListValue);
    }

    @Test
    public void testSortingHashMapLocal() {
        HashMap<FoodItem, Integer> testCountPopulatedItem = new HashMap<>();
        FoodItem foodItem1 = new FoodItem("foodItem1", 21.00);
        FoodItem foodItem2 = new FoodItem("foodItem2", 10.00);
        testCountPopulatedItem.put(foodItem1, 1);
        testCountPopulatedItem.put(foodItem2, 2);
        LinkedHashMap<FoodItem, Integer> expectedList = new LinkedHashMap<>();
        expectedList.put(foodItem2, 2);
        expectedList.put(foodItem1, 1);
        Assertions.assertEquals(suggestionToUserInteractor.sortingHashMap(testCountPopulatedItem), expectedList);
    }

    @Test
    public void testGetFinalSuggestion() {
        PastOrders testOrder = suggestionToUserDatabase.findPastOrders(testUser);
        assert testOrder != null;
        final HashMap<String, Order> pastOrdersMap = testOrder.getPastOrdersMap();
        HashMap<FoodItem, Integer> currentItemCount = suggestionToUserInteractor.itemCount(testOrder, pastOrdersMap);
        LinkedHashMap<FoodItem, Integer> sortedList = suggestionToUserInteractor.sortingHashMap(currentItemCount);
        ArrayList<String> actualList = suggestionToUserInteractor.getFinalSuggestion(testUser, sortedList);
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("Falafel Wrap");
        expectedList.add("Chicken Shawarma");
        Assertions.assertEquals(actualList, expectedList);
    }

    @Test
    public void testGetSuggestionToUser() {
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("Falafel Wrap");
        expectedList.add("Chicken Shawarma");
        ArrayList<String> actualList = suggestionToUserInteractor.getSuggestionToUser(testUser);
        Assertions.assertEquals(actualList, expectedList);
    }

    @Test
    public void testGetSuggestionToUserNoItem() {
        ArrayList<String> expectedList = new ArrayList<>();
        ArrayList<String> actualList = suggestionToUserInteractor.getSuggestionToUser(testUser2);
        Assertions.assertEquals(actualList, expectedList);
    }

}
