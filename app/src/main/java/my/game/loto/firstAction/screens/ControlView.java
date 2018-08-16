package my.game.loto.firstAction.screens;


import java.util.List;

import my.game.loto.firstAction.retrofit.settingsObjects.PlayObject;

public interface ControlView {

        void nextChoiceFragment(String[] preferences);
        void nextWaitFragment();
        void showLoadingError();
        void nextSecondActivity(List<PlayObject> playObject);
    }




