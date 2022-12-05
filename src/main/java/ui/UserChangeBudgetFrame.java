package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserChangeBudgetFrame extends JFrame{

    private static JLabel enterBudgetLabel;
    private static JTextField userText;
    private static JLabel confirmBudgetLabel;
    private static JButton enterButton;
    private static JButton backButton;
    private static JLabel successChange;

    public UserChangeBudgetFrame() {

        JPanel panel = new JPanel();
        this.setTitle("Change Budget");
        this.setSize(420, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);



        enterBudgetLabel = new JLabel("Enter New Monthly Budget:");
        enterBudgetLabel.setBounds(10, 20, 200, 25);
        panel.add(enterBudgetLabel);
        // text field for the user to input their username
        userText = new JTextField();
        userText.setBounds(200, 20 , 165, 25);
        panel.add(userText);


        confirmBudgetLabel = new JLabel("Confirm New Monthly Budget:");
        confirmBudgetLabel.setBounds(10, 50, 200, 25);
        panel.add(confirmBudgetLabel);
        userText = new JTextField();
        userText.setBounds(200, 50 , 165, 25);
        panel.add(userText);


        // enter button
        enterButton = new JButton("Enter");
        enterButton.setBounds(10, 85, 80, 25);
        panel.add(enterButton);

        backButton = new JButton("Back");
        backButton.setBounds(10, 120, 80, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                UserPageFrame userPageFrame = new UserPageFrame();
            }
        });
        panel.add(backButton);

        successChange = new JLabel();
        successChange.setBounds(10, 110, 300, 25);
        panel.add(successChange);


        this.setVisible(true);

    }

    private void exit() {
        this.setVisible(false);
        this.dispose();
    }
}
