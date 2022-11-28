package usecases.filtering.PriceFiltering;

import entities.FoodItem;

import java.util.ArrayList;
import java.util.HashMap;

public class priceFilter {

    private HashMap<String, ArrayList<FoodItem>> filter;

    private String filterName;

    /**
     *
     * @param filter
     * @param filterName
     * Instantiates the instances of priceFiltering
     */
    public priceFilter(HashMap<String, ArrayList<FoodItem>> filter, String filterName) {
        this.filter = filter;
        this.filterName = filterName;
    }

    /**
     *
     * @param p_range
     * @return
     * the filter method returns a food item after checking whether it is included in a price range
     * if it is not in the price range it then returns null
     */
    public ArrayList<FoodItem> filter(String p_range) {
        return filter.getOrDefault(p_range, null);
    }
}
