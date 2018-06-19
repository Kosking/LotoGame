package com.example.work.loto;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;

public class ChoicePresenter  {


    private LifecycleHandler lifecycleHandler;
    private ControlView controlView;
    private ConnectRepository connectingRepository;
    private String stringsSharedPreferences[];


    public ChoicePresenter(@NonNull ControlView controlView,
                           @NonNull LifecycleHandler lifecycleHandler,
                           @NonNull ConnectRepository connectingRepository) {
        this.connectingRepository = connectingRepository;
        this.controlView = controlView;
        this.lifecycleHandler = lifecycleHandler;

    }

    /*public void init() {
        String token = PreferenceObject.getToken();
        if (!TextUtils.isEmpty(token)) {
            mainActivity.openPlayingScreen();
        }
    }*/

    public void onNextChoiceFragment(){
        connectingRepository
                .getPreferences()
                .compose(lifecycleHandler.load(R.id.preferences))
                .subscribe(preferencesObject -> controlView.nextChoiceFragment(preferencesObject),
                        throwable -> controlView.showLoadingError());
    }

    public void setStringsPreferences(String stringsPreferences[]){
        stringsSharedPreferences = stringsPreferences;
        connectingRepository.setPreferences(stringsSharedPreferences);
    }

    public void onNextWaitFragment(){
        controlView.nextWaitFragment();
    }

}
