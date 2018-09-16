package my.game.loto.choiceAction.screens;


import java.util.List;

import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;

public interface ControlView {

    void nextChoiceFragment(String[] preferences);
    void nextWaitFragment();
    void showLoadingError();
    void nextSecondActivity(List<PlayObject> playObject);
    void setPlayerName(String playerName);
}




