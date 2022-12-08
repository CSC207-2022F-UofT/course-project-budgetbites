package ui;

import controllers.ItemCartAndOrderController;
import gateways.MainMongoDB;
import presenters.ItemCartPresenter;
import usecases.BudgetDAI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemCartFrame extends JFrame implements ActionListener {
    private final BudgetDAI budgetDAI = new MainMongoDB();
    private static JButton backButton;
    private static JButton removeFromCartButton;
    private static JButton makeOrderButton;
    private ItemCartPresenter itemCartPresenter;
    private String restaurantName;
    private ItemCartAndOrderController itemCartAndOrderController;
    private String itemCartCost;
    private static JLabel itemCartCostLabel;
    private String currentUser;

    public ItemCartFrame(ItemCartAndOrderController itemCartAndOrderController, String restaurantName, String currentUser) {
        this.currentUser = currentUser;
        this.restaurantName = restaurantName;
        this.itemCartAndOrderController = itemCartAndOrderController;
        this.itemCartPresenter = new ItemCartPanel(itemCartAndOrderController);
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();

        this.setTitle("BudgetBites-ItemCart");
        this.setSize(420, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        itemCartCost = "Price of Cart: $" + itemCartAndOrderController.getItemCartCost();
        itemCartCostLabel = new JLabel(itemCartCost);
        itemCartCostLabel.setBounds(10, 20, 120, 25);
        top.add(itemCartCostLabel);

        backButton = new JButton("Back");
        backButton.setBounds(10, 110, 160, 25);
        backButton.addActionListener(this);

        removeFromCartButton = new JButton("Remove From Cart");
        removeFromCartButton.setBounds(10, 110, 160, 25);
        removeFromCartButton.addActionListener(this);

        makeOrderButton = new JButton("Order");
        makeOrderButton.setBounds(10, 110, 160, 25);
        makeOrderButton.addActionListener(this);

        bottom.add(backButton);
        bottom.add(removeFromCartButton);
        bottom.add(makeOrderButton);

        itemCartPresenter.foodInCart();

        this.add(top);
        this.add((JPanel) itemCartPresenter);
        this.add(bottom);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        ItemCartAndOrderController controller = new ItemCartAndOrderController("Food from East");
        controller.addToItemCart("Chicken Shawarma - Price $5");
        new ItemCartFrame(controller, "Food from East", "mann1234");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == backButton) {
            exit();
            new FoodItemsFrame(restaurantName, currentUser);
        } else if (obj == removeFromCartButton) {
            JList<String> selection = itemCartPresenter.getList();
            String choice = selection.getSelectedValue();
            itemCartAndOrderController.removeFromItemCart(choice);
            itemCartPresenter.foodInCart();
            itemCartCost = "Price of Cart: $" + itemCartAndOrderController.getItemCartCost();
            itemCartCostLabel.setText(itemCartCost);
            this.validate();
        } else if (obj == makeOrderButton) {
            itemCartAndOrderController.makeOrder(restaurantName, currentUser);
            exit();
            UserPageFrame userPageFrame = new UserPageFrame(currentUser);
        }

    }

    private void exit() {
        this.setVisible(false);
        this.dispose();
    }
}
