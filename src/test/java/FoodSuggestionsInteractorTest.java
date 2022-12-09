import entities.FoodItem;
import entities.Order;
import entities.PastOrders;
import gateways.MainMongoDB;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import usecases.SuggestionToUserDAI;
import usecases.foodsuggestions.FoodSuggestionsInteractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class FoodSuggestionsInteractorTest {

    private final SuggestionToUserDAI suggestionToUserDatabase = new MainMongoDB();
    private final FoodSuggestionsInteractor foodSuggestionsInteractorInteractor = new FoodSuggestionsInteractor(suggestionToUserDatabase);

    private final String testUser = "aryangoel24";

    @Test
    public void testSortingHashMapMongo() {
        PastOrders testOrder = suggestionToUserDatabase.findPastOrders(testUser);
        assert testOrder != null;
        final HashMap<String, Order> pastOrdersMap = testOrder.getPastOrdersMap();
        LinkedHashMap<FoodItem, Integer> testSortingHashMap = foodSuggestionsInteractorInteractor.sortingHashMap(foodSuggestionsInteractorInteractor.itemCount(testOrder, pastOrdersMap));
        List<Integer> expectedValues = new ArrayList<>(testSortingHashMap.values());
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
        Assertions.assertEquals(foodSuggestionsInteractorInteractor.sortingHashMap(testCountPopulatedItem), expectedList);
    }

    @Test
    public void testGetFinalSuggestion() {
        PastOrders testOrder = suggestionToUserDatabase.findPastOrders(testUser);
        assert testOrder != null;
        final HashMap<String, Order> pastOrdersMap = testOrder.getPastOrdersMap();
        HashMap<FoodItem, Integer> currentItemCount = foodSuggestionsInteractorInteractor.itemCount(testOrder, pastOrdersMap);
        LinkedHashMap<FoodItem, Integer> sortedList = foodSuggestionsInteractorInteractor.sortingHashMap(currentItemCount);
        ArrayList<String> actualList = foodSuggestionsInteractorInteractor.getFinalSuggestion(testUser, sortedList);
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
        ArrayList<String> actualList = foodSuggestionsInteractorInteractor.getSuggestionToUser(testUser);
        Assertions.assertEquals(actualList, expectedList);
    }

    @Test
    public void testGetSuggestionToUserNoItem() {
        ArrayList<String> expectedList = new ArrayList<>();
        String testUser2 = "darpanmi";
        expectedList.add("Oops " + testUser2 + " it looks like you don't have enough past orders");
        expectedList.add("for me to suggest you your favourite food items");
        ArrayList<String> actualList = foodSuggestionsInteractorInteractor.getSuggestionToUser(testUser2);
        Assertions.assertEquals(actualList, expectedList);
    }

}
