package presenters;

import javax.swing.*;

public interface ItemCartPresenter {
    void foodInCart();

    JList<String> getList();
}
