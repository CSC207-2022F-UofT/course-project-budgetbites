import controllers.FoodSuggestionsController;
import gateways.MainMongoDB;
import presenters.FoodSuggestionPresenter;
import ui.userSuggestionFrame;
import usecases.SuggestionToUserDAI;
import usecases.foodsuggestions.SuggestionToUser;

public class main{

    public static void main(String[] args) {
        SuggestionToUserDAI dataBase = new MainMongoDB();
        FoodSuggestionPresenter presenter = new FoodSuggestionPresenter();
        SuggestionToUser suggestionToUser = new SuggestionToUser(dataBase);
        FoodSuggestionsController controller = new FoodSuggestionsController();
        userSuggestionFrame test = new userSuggestionFrame();

        controller.setUseCase(suggestionToUser);
        suggestionToUser.setPresenter(presenter);
        presenter.setViewer(test);
        test.setFoodSuggestionsController(controller);
    }
}
