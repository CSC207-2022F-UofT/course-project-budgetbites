package ui;

import ui.UserPageFrame;
import gateways.MainMongoDB;
import usecases.BudgetDAI;
import entities.Budget;
import usecases.budgeting.BudgetManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Double.parseDouble;


public class UserChangeBudgetFrame extends JFrame{

    private final BudgetDAI budgetDAI = new MainMongoDB();

    private final BudgetManager budgetManager = new BudgetManager();
    private static JLabel enterUserNameLabel;
    private static JLabel enterBudgetLabel;
    private static JTextField userNameText;
    private static JTextField budgetText;
    private static JTextField confirmBudgetText;
    private static JLabel confirmBudgetLabel;
    private static JButton enterButton;
    private static JButton backButton;
    private static JLabel successChange;
    private String currentUser;

    public UserChangeBudgetFrame(String currentUser) {

        JPanel panel = new JPanel();
        this.currentUser = currentUser;
        this.setTitle("Change Budget");
        this.setSize(420, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);


        enterUserNameLabel = new JLabel("Enter Your UTORid:");
        enterUserNameLabel.setBounds(10, 20, 200, 25);
        panel.add(enterUserNameLabel);
        // text field for the user to input their username
        userNameText = new JTextField();
        userNameText.setBounds(200, 20 , 165, 25);
        panel.add(userNameText);

        enterBudgetLabel = new JLabel("Enter New Monthly Budget:");
        enterBudgetLabel.setBounds(10, 50, 200, 25);
        panel.add(enterBudgetLabel);
        budgetText = new JTextField();
        budgetText.setBounds(200, 50 , 165, 25);
        panel.add(budgetText);


        confirmBudgetLabel = new JLabel("Confirm New Monthly Budget:");
        confirmBudgetLabel.setBounds(10, 80, 200, 25);
        panel.add(confirmBudgetLabel);
        confirmBudgetText = new JTextField();
        confirmBudgetText.setBounds(200, 80 , 165, 25);
        panel.add(confirmBudgetText);


        // enter button
        enterButton = new JButton("Enter");
        enterButton.setBounds(10, 115, 80, 25);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = userNameText.getText();
                double budget = Double.parseDouble(budgetText.getText());
                double confirmBudget = Double.parseDouble(confirmBudgetText.getText());

                if (budgetDAI.userExists(userName)) {
                        if (BudgetManager.newBudgetConfirm(budget, confirmBudget)) {
                            Budget userBudget = (Budget) budgetDAI.getUserAttribute(userName, "budget");
                            BudgetManager.adjustMonthlyBudget(userBudget, budget);
                            budgetDAI.updateAttributeByUsername(userName, "budget", userBudget);
                            successChange.setText("Budget Updated");
                            exit();
                            UserPageFrame userPageFrame = new UserPageFrame(currentUser);
                        } else {
                            successChange.setText("Invalid Inputs, please enter values again.");
                        }
                    } else
                        successChange.setText("User does not exist");
                }
            });

        panel.add(enterButton);

        backButton = new JButton("Back");
        backButton.setBounds(10, 150, 80, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                UserPageFrame userPageFrame = new UserPageFrame(currentUser);
            }
        });
        panel.add(backButton);

        successChange = new JLabel();
        successChange.setBounds(10, 180, 300, 25);
        panel.add(successChange);


        this.setVisible(true);

    }

    private void exit() {
        this.setVisible(false);
        this.dispose();
    }
}