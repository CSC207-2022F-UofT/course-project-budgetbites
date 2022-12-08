package controllers;

import usecases.foodsuggestions.SuggestionToUser;

public class FoodSuggestionsController {
    private SuggestionToUser suggestionToUser;

    public void setUseCase(SuggestionToUser s){
        this.suggestionToUser = s;
    }


    public void callUseCase(String username) {
//        System.out.println("line 11 controller");
        suggestionToUser.toPresenter(username);
    }

}
