package my.game.loto.choiceAction.screens;


import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;

public interface ControlView {

    void nextChoiceFragment(String[] preferences);
    void nextWaitFragment();
    void showLoadingError();
    void nextSecondActivity();
    void setFragment(String playerName, PrimaryData primaryData);
}




