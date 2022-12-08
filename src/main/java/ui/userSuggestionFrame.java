package ui;

import controllers.FoodSuggestionsController;
import presenters.FoodSuggestionPresenter;
import view.FoodSuggestionViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class userSuggestionFrame extends JFrame implements FoodSuggestionViewer {

    private static JButton button;
    private static JLabel label;

    private FoodSuggestionsController foodSuggestionsController;
    private ArrayList<String> returnArrayList;

    public void setFoodSuggestionsController(FoodSuggestionsController foodSuggestionsController) {
        this.foodSuggestionsController = foodSuggestionsController;
    }

    public userSuggestionFrame() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setTitle("BudgetBites - Suggestions");
        this.setSize(new Dimension(420,600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);

        // Label for action performed on button click
        label = new JLabel("xxx");
        label.setBounds(10, 20, 100, 25);




        JScrollPane scrollPanel = new JScrollPane();
//        this.add(scrollPanel);
        JList<String> r = new JList<>();
        scrollPanel.setViewportView(r);

        r.setLayoutOrientation(JList.VERTICAL);

        panel.add(scrollPanel);


        button = new JButton("Button");
        button.setBounds(10, 230, 165, 25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                foodSuggestionsController.callUseCase("aryangoel24");
                System.out.println(returnArrayList.toString());
//                panel.add(label);
            }
        });
        panel.add(button);


        this.setVisible(true);
    }

    @Override
    public void viewData(ArrayList<String> viewData) {
        returnArrayList = viewData;
    }
}
