package ui;

import entities.Budget;
import gateways.MainMongoDB;
import usecases.BudgetDAI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserPageFrame extends JFrame{

    private final BudgetDAI budgetDAI = new MainMongoDB();
    private static JLabel userFirstNameLabel;
    private static JLabel userLastNameLabel;
    private static JLabel userBudgetLabel;
    private static JLabel userCurrentBudgetLabel;
    private static JLabel firstNameLabel;
    private static JLabel lastNameLabel;
    private static JLabel monthlyBudgetLabel;
    private static JLabel currentBudgetLabel;
    private static JButton changeBudgetButton;
    private static JButton viewPastOrdersButton;
    private static JButton backButton;
    private String currentUser;

    public UserPageFrame(String currentUser) {
        JPanel panel = new JPanel();
        this.setTitle("UserProfile");
        this.setSize(420, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.currentUser = currentUser;

        panel.setLayout(null);


        // the first name label
        userFirstNameLabel = new JLabel("First Name:");
        userFirstNameLabel.setBounds(10, 20, 80, 25);
        panel.add(userFirstNameLabel);
        // text field for the user to input their username
        Object firstName = budgetDAI.getUserAttribute(currentUser, "firstName");
        firstNameLabel = new JLabel((String) firstName);
        firstNameLabel.setBounds(120, 20 , 165, 25);
        panel.add(firstNameLabel);

        // the last name label
        userLastNameLabel = new JLabel("Last Name:");
        userLastNameLabel.setBounds(10, 50, 80, 25);
        panel.add(userLastNameLabel);
        Object lastName = budgetDAI.getUserAttribute(currentUser, "lastName");
        lastNameLabel = new JLabel((String) lastName);
        lastNameLabel.setBounds(120, 50 , 165, 25);
        panel.add(lastNameLabel);

        // the initial monthly budget label
        userBudgetLabel = new JLabel("Monthly Budget:");
        userBudgetLabel.setBounds(10, 80, 120, 25);
        panel.add(userBudgetLabel);
        Budget monthlyBudget = (Budget) budgetDAI.getUserAttribute(currentUser, "budget");
        double mBudget = monthlyBudget.getInitialBudget();
        monthlyBudgetLabel = new JLabel("$" + String.valueOf(mBudget));
        monthlyBudgetLabel.setBounds(120, 80 , 165, 25);
        panel.add(monthlyBudgetLabel);

        // the current budget label
        userCurrentBudgetLabel = new JLabel("Current Budget:");
        userCurrentBudgetLabel.setBounds(10, 110, 120, 25);
        panel.add(userCurrentBudgetLabel);
        Budget currBudget = (Budget) budgetDAI.getUserAttribute(currentUser, "budget");
        double cBudget = currBudget.getCurrentBudget();
        currentBudgetLabel = new JLabel("$" + String.valueOf(cBudget));
        currentBudgetLabel.setBounds(120, 110 , 165, 25);
        panel.add(currentBudgetLabel);


        changeBudgetButton = new JButton("Change Monthly Budget");
        changeBudgetButton.setBounds(10, 145, 180, 25);
        changeBudgetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                UserChangeBudgetFrame userChangeBudgetFrame = new UserChangeBudgetFrame(currentUser);
            }
        });
        panel.add(changeBudgetButton);

        viewPastOrdersButton = new JButton("View Past Orders");
        viewPastOrdersButton.setBounds(10, 175, 180, 25);
        viewPastOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                PastOrdersFrame pastOrdersFrame = new PastOrdersFrame();
            }
        });
        panel.add(viewPastOrdersButton);

        backButton = new JButton("Back");
        backButton.setBounds(10, 205, 80, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                RestaurantListFrame restaurantListFrame = new RestaurantListFrame(currentUser);
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
