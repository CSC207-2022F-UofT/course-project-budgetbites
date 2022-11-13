package Use_Cases;

import Entities.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;

public class restaurantFilter {
    private HashMap<String, ArrayList<Restaurant>> filter;
    private String filterName;

    public restaurantFilter(HashMap<String, ArrayList<Restaurant>> filter, String filterName){
        this.filter = filter;
        this.filterName = filterName;
    }

    public ArrayList<Restaurant> filter(String choice) {
        return filter.getOrDefault(choice, null);
    }

//    public static void main(String[] args) {
//        HashMap<String, ArrayList<Restaurant>> temp = new HashMap<>();
//        ArrayList<Restaurant> ltemp = new ArrayList<>();
//        ltemp.add(new Restaurant("$$$", "Chinese", "Dinner", 3.2, null));
//        ltemp.add(new Restaurant("$$$", "Chinese", "Breakfast", 3.1, null));
//        ltemp.add(new Restaurant("$$", "Chinese", "Lunch", 3.2, null));
//        temp.put("Chinese", ltemp);
//        restaurantFilter filter1 = new restaurantFilter(temp, "Cuisine");
//        System.out.println(filter1.filter("afd"));
//    }
}
