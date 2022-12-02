package usecases.foodsuggestions;
import entities.Budget;
import entities.FoodItem;
import entities.Order;
import entities.PastOrders;
import gateways.MainMongoDB;
import usecases.SuggestionToUserDAI;

import java.util.*;

public class SuggestionToUser {

    private final SuggestionToUserDAI suggestionToUserDAI = new MainMongoDB();

    /**
     * Takes the userName and returns the items that can be ordered again based ont the
     * number of times it was ordered previously.
     * @param userName of the current User
     * @return ArrayList of the final suggestion (list of food items) based on the budget
     */
    public ArrayList<String> getSuggestionToUser(String userName) {
        PastOrders allPastOrder = suggestionToUserDAI.findPastOrders(userName);
        assert allPastOrder != null;
        final HashMap<String, Order> pastOrdersMap = allPastOrder.getPastOrdersMap();
        HashMap<FoodItem, Integer> currentItemCount = itemCount(allPastOrder, pastOrdersMap);
        LinkedHashMap<FoodItem, Integer> sortedList = sortingHashMap(currentItemCount);
        return getFinalSuggestion(userName, sortedList);
    }

    /**
     * Takes the pastOrder and pastOrderMap to create a hashmap with itemEntity and its corresponding repeation
     * in the user's pastOrder
     * @param allPastOrder Extracted from the MongoDB based on the given userName.
     * @param pastOrderMap created from MongoDB of all the pastOrder HashMap with dateTime and Order pair.
     * @return Hashmap with FoodItem and Integer key-value pair.
     */
    public HashMap<FoodItem, Integer> itemCount(PastOrders allPastOrder, HashMap<String, Order> pastOrderMap) {
        HashMap<FoodItem, Integer> countItems = new HashMap<>();
        HashMap<String, FoodItem> visitedItem = new HashMap<>();
        ArrayList<String> allOrderDateTime = new ArrayList<>(pastOrderMap.keySet());
        for (int i = 0; i < allOrderDateTime.size(); i++)
            for (FoodItem orderedItem : allPastOrder.getOrderedItemsByDate(allOrderDateTime.get(i))) {
                if (!visitedItem.containsKey(orderedItem.getItemName())) {
                    countItems.put(orderedItem, 1);
                    visitedItem.put(orderedItem.getItemName(), orderedItem);
                } else {
                    countItems.replace(visitedItem.get(orderedItem.getItemName()),
                            countItems.get(visitedItem.get(orderedItem.getItemName()))+ 1);
                }
            }
        return countItems;
    }

    /**
     * Sort in decreasing order based on the number of times a FoodItem was ordered.
     * @param populatedCountItem
     * @return Linked Hashmap which is sorted of the FoodItem objects
     */
    public LinkedHashMap<FoodItem, Integer> sortingHashMap(HashMap<FoodItem, Integer> populatedCountItem) {
        ArrayList<Integer> list = new ArrayList<>();
        LinkedHashMap<FoodItem, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<FoodItem, Integer> entry : populatedCountItem.entrySet()) {
            list.add(entry.getValue());
        }
        list.sort(Collections.reverseOrder());
        for (int num : list) {
            for (Map.Entry<FoodItem, Integer> entry : populatedCountItem.entrySet()) {
                if (entry.getValue().equals(num)) {
                    sortedMap.put(entry.getKey(), num);
                }
            }
        }
        return sortedMap;
    }

    /**
     * List of foods that can be ordered with the given current budget.
     * @param userName userName of the current user
     * @param populatedSortedMap populatedSorted Map contains FoodItem object and their repeation value.
     * @return Final suggestion List which contains Strings
     */
    public ArrayList<String> getFinalSuggestion(String userName, LinkedHashMap<FoodItem, Integer> populatedSortedMap) {
        double budgetSoFar = 0.00;
//        double currentBudget = (double) MainMongoDB.getUserAttribute(userName, "budget");
        double currentBudget = ((Budget) Objects.requireNonNull(suggestionToUserDAI.getUserAttribute(userName, "budget"))).getCurrentBudget();
        ArrayList<String> finalSuggestion = new ArrayList<>();
        for (FoodItem item: populatedSortedMap.keySet()) {
            budgetSoFar += item.getItemCost();
            if (budgetSoFar > currentBudget) {
                break;
            }
            finalSuggestion.add(item.getItemName());
        }
        return finalSuggestion;
    }
}
