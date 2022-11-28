package ui;

import javax.swing.*;
import java.util.*;

public class RestaurantListingsFrame extends JFrame{
    // Table
    JTable j;
    private static JButton but1;
    private static JButton but2;


    List<Map<String, Object>> restaurantList = new ArrayList<Map<String, Object>>();
    Map<String, Object> r1 = new HashMap<String, Object>();
    Map<String, Object> r2 = new HashMap<String, Object>();
    Map<String, Object> r3 = new HashMap<String, Object>();

    JLabel jLabel;

    // Constructor
    public RestaurantListingsFrame() {

        r1.put("name", "Blue Food Truck");
        r1.put("priceRange", "$$$$");
        r1.put("cuisine", "Japanese");
        r1.put("foodType", "Dinner");
        r1.put("avgRating", 1.2);

        r2.put("name", "Brown Food Truck");
        r2.put("priceRange", "$");
        r2.put("cuisine", "Indian");
        r2.put("foodType", "Snacks");
        r2.put("avgRating", 4.8);

        r3.put("name", "Red Food Truck");
        r3.put("priceRange", "$$");
        r3.put("cuisine", "Chinese");
        r3.put("foodType", "Lunch");
        r3.put("avgRating", 4.2);

        restaurantList.add(r1);
        restaurantList.add(r2);
        restaurantList.add(r3);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.setTitle("BudgetBites - Restaurant Listings");
        this.setSize(420, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        int start = 20;
        for(int i=0; i<3; i++){
            SingleRestaurant singleRestaurant = new SingleRestaurant(restaurantList.get(i).get("name"), restaurantList.get(i).get("priceRange"), restaurantList.get(i).get("cuisine"), restaurantList.get(i).get("foodType"), restaurantList.get(i).get("avgRating"), panel, start);
            start += 160;
        }

        this.setVisible(true);

    }
}