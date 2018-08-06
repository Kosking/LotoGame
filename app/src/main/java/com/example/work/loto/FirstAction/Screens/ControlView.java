package com.example.work.loto.FirstAction.Screens;


import com.example.work.loto.FirstAction.Repository.Retrofit.SettingsObjects.PlayObject;

import java.io.IOException;
import java.util.List;

public interface ControlView {

        void nextChoiceFragment(String[] preferences);
        void nextWaitFragment();
        void showLoadingError();
        void nextToSecondActivity(List<PlayObject> playObject)throws IOException;
    }




