package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{

    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton loginButton;
    private static JButton backButton;
    private static JLabel successLogin;

    public LoginFrame() {

        JPanel panel = new JPanel();
        this.setTitle("BudgetBites");
        this.setSize(420, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);


        // the user label for user log in text
        userLabel = new JLabel("UTORid:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);
        // text field for the user to input their username
        userText = new JTextField();
        userText.setBounds(100, 20 , 165, 25);
        panel.add(userText);

        // the password label for password log in text
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);
        // text field for the user to input their password, characters entered will not be visible
        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50 , 165, 25);
        panel.add(passwordText);


        // login button
        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                UserPreferenceFrame userPreferenceFrame = new UserPreferenceFrame();
            }
        });
        panel.add(loginButton);

        backButton = new JButton("Back");
        backButton.setBounds(10, 120, 80, 25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                OnboardingFrame onboardingFrame = new OnboardingFrame();
            }
        });
        panel.add(backButton);

        // login label to return message upon login attempt
        successLogin = new JLabel();
        successLogin.setBounds(10, 110, 300, 25);
        panel.add(successLogin);


        this.setVisible(true);

    }

    private void exit() {
        this.setVisible(false);
        this.dispose();
    }
}
