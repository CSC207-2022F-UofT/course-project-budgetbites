package usecases.foodsuggestions;
import entities.Budget;
import entities.FoodItem;
import entities.Order;
import entities.PastOrders;
import usecases.MainMongoDB;

import java.util.*;

public class suggestionToUser {

//    public static void main(String[] args) {
//        //First created order by findPastOrders
//        PastOrders order = MainMongoDB.findPastOrders("aryangoel24");
//        assert order != null;
//        //Extracted a hashmap with datetime to order
//        final HashMap<String, Order> pastOrdersMap = order.getPastOrdersMap();
//        //Created an array of all those datetimes.
//        System.out.println(getFinalSuggestion("aryangoel24" ,sortingHashMap(itemCount(order, pastOrdersMap))));
//    }

    /**
     * Takes the userName and returns the items that can be ordered again based ont the
     * number of times it was ordered previously.
     * @param userName of the current User
     * @return ArrayList of the final suggestion (list of food items) based on the budget
     */

    public static ArrayList<String> getSuggestionToUser(String userName) {
        PastOrders allPastOrder = MainMongoDB.findPastOrders(userName);
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
    public static HashMap<FoodItem, Integer> itemCount(PastOrders allPastOrder, HashMap<String, Order> pastOrderMap) {
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
    private static LinkedHashMap<FoodItem, Integer> sortingHashMap(HashMap<FoodItem, Integer> populatedCountItem) {
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
     * @param userName
     * @param populatedSortedMap
     * @return Final suggestion List which contains Strings
     */
    private static ArrayList<String> getFinalSuggestion(String userName, LinkedHashMap<FoodItem, Integer> populatedSortedMap) {
        double budgetSoFar = 0.00;
//        double currentBudget = (double) MainMongoDB.getUserAttribute(userName, "budget");
        double currentBudget = ((Budget) Objects.requireNonNull(MainMongoDB.getUserAttribute(userName, "budget"))).getCurrentBudget();
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
