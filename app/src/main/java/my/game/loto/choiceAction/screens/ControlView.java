package my.game.loto.choiceAction.screens;


import my.game.loto.choiceAction.repository.StartObject;

public interface ControlView {

    void nextChoiceFragment(String[] preferences);
    void nextWaitFragment();
    void showLoadingError();
    void nextSecondAction();
    void setFragment(StartObject startObject);
}




