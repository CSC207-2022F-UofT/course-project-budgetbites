package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SingleRestaurant extends JPanel{
    // Table
    JTable j;
    private Object name;
    private Object priceRange;
    private Object cuisine;
    private Object foodType;
    public Object avgRating;

    private JLabel nameLabel;
    private JLabel priceRangeLabel;
    private JLabel cuisineLabel;
    private JLabel foodTypeLabel;
    public JLabel avgRatingLabel;


    // Constructor
    public SingleRestaurant(Object name, Object priceRange, Object cuisine, Object foodType, Object avgRating, JPanel panel, Integer start) {

        this.name = name;
        this.priceRange = priceRange;
        this.cuisine = cuisine;
        this.foodType = foodType;
        this.avgRating = avgRating;

        nameLabel =new JLabel("<html> <font size=\"+2\"> <b>" + name + "</b> </font> </html>");
        priceRangeLabel =new JLabel("<html> Price Range: " + priceRange + "</html>");
        cuisineLabel =new JLabel("<html> Cuisine: " + cuisine + "</html>");
        foodTypeLabel =new JLabel("<html> Food Type: " + foodType + "</html>");
        avgRatingLabel =new JLabel("<html> Average Rating: " + avgRating + " stars</html>");

        nameLabel.setBounds(100, start, 420, 30);
        priceRangeLabel.setBounds(55, start+50, 280, 25);
        avgRatingLabel.setBounds(205, start+50, 280, 25);
        cuisineLabel.setBounds(55, start+90, 280, 25);
        foodTypeLabel.setBounds(205, start+90, 280, 25);

        panel.add(nameLabel);
        panel.add(priceRangeLabel);
        panel.add(cuisineLabel);
        panel.add(foodTypeLabel);
        panel.add(avgRatingLabel);

        this.setVisible(true);
    }
}