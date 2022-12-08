package ui;

import controllers.ItemCartAndOrderController;
import presenters.ItemCartPresenter;

import javax.swing.*;
import java.awt.*;

public class ItemCartPanel extends JPanel implements ItemCartPresenter {
    private JScrollPane scroll;
    private ItemCartAndOrderController controller;
    private JList<String> list;

    public ItemCartPanel(ItemCartAndOrderController controller) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(420,500));
        scroll = new JScrollPane();
        this.controller = controller;
        list = new JList<>();
        scroll.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        this.add(scroll);
    }
    @Override
    public void foodInCart() {
        DefaultListModel<String> updatedModel = new DefaultListModel<>();
        for(String val : controller.getItems())
            updatedModel.addElement(val);
        list = new JList<>(updatedModel);
        scroll.setViewportView(list);
    }

    @Override
    public JList<String> getList() {
        return list;
    }
}
