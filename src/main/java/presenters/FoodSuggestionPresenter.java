package presenters;

import view.FoodSuggestionViewer;

import java.util.ArrayList;

public class FoodSuggestionPresenter implements IFoodSuggestionsPresenter {
    private FoodSuggestionViewer viewer;

    public void setViewer(FoodSuggestionViewer viewer) {
        this.viewer = viewer;
    }

    @Override
    public void presentData(ArrayList<String> data) {
        viewer.viewData(data);
    }
}
