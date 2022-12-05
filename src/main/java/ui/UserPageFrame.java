package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserPageFrame extends JFrame{

    private static JLabel userFirstNameLabel;
    private static JTextField userText;
    private static JLabel userLastNameLabel;
    private static JLabel userBudgetLabel;
    private static JLabel userCurrentBudgetLabel;
    private static JButton changeBudgetButton;
    private static JButton backButton;

    public UserPageFrame() {

        JPanel panel = new JPanel();
        this.setTitle("UserProfile");
        this.setSize(420, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);


        // the first name label
        userFirstNameLabel = new JLabel("First Name:");
        userFirstNameLabel.setBounds(10, 20, 80, 25);
        panel.add(userFirstNameLabel);
        // text field for the user to input their username
        userText = new JTextField();
        userText.setBounds(120, 20 , 165, 25);
        panel.add(userText);

        // the last name label
        userLastNameLabel = new JLabel("Last Name:");
        userLastNameLabel.setBounds(10, 50, 80, 25);
        panel.add(userLastNameLabel);
        userText = new JTextField();
        userText.setBounds(120, 50 , 165, 25);
        panel.add(userText);

        // the initial monthly budget label
        userBudgetLabel = new JLabel("Monthly Budget:");
        userBudgetLabel.setBounds(10, 80, 120, 25);
        panel.add(userBudgetLabel);
        userText = new JTextField();
        userText.setBounds(120, 80 , 165, 25);
        panel.add(userText);

        // the current budget label
        userCurrentBudgetLabel = new JLabel("Current Budget:");
        userCurrentBudgetLabel.setBounds(10, 110, 120, 25);
        panel.add(userCurrentBudgetLabel);
        userText = new JTextField();
        userText.setBounds(120, 110 , 165, 25);
        panel.add(userText);


        changeBudgetButton = new JButton("Change Monthly Budget");
        changeBudgetButton.setBounds(10, 145, 180, 25);
        changeBudgetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                UserChangeBudgetFrame userChangeBudgetFrame = new UserChangeBudgetFrame();
            }
        });
        panel.add(changeBudgetButton);


        backButton = new JButton("Back");
        backButton.setBounds(10, 175, 80, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                RestaurantListFrame restaurantListFrame = new RestaurantListFrame();
            }
        });
        panel.add(backButton);


        this.setVisible(true);

    }

    private void exit() {
        this.setVisible(false);
        this.dispose();
    }
}