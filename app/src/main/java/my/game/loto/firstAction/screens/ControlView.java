package my.game.loto.firstAction.screens;


import java.util.List;

import my.game.loto.firstAction.retrofit.SettingsObjects.PlayObject;

public interface ControlView {

        void nextChoiceFragment(String[] preferences);
        void nextWaitFragment();
        void showLoadingError();
        void nextToSecondActivity(List<PlayObject> playObject);
    }



