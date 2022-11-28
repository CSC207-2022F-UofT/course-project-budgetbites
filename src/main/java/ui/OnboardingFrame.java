
package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnboardingFrame extends JFrame{

    private static JLabel userLabel;
    private static JButton loginButton;
    private static JButton signinButton;
    private static JLabel successLogin;

    public OnboardingFrame(){
        JPanel panel = new JPanel();
        this.setTitle("BudgetBites - Welcome");
        this.setSize(420, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        panel.setLayout(null);


        // the user label for user log in text
        userLabel = new JLabel("Welcome! Login or Create a new account");
        userLabel.setBounds(85, 20, 280, 25);
        panel.add(userLabel);


        // login button
        loginButton = new JButton("Login");
        loginButton.setBounds(160, 60, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                LoginFrame loginFrame = new LoginFrame();
            }
        });
        panel.add(loginButton);

        // SignUp button
        signinButton = new JButton("Sign Up");
        signinButton.setBounds(160, 100, 80, 25);
        signinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
                SignUpFrame signUpFrame = new SignUpFrame();
            }
        });
        panel.add(signinButton);


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
