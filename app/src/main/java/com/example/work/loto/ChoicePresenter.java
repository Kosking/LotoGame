package com.example.work.loto;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.LifecycleHandler;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChoicePresenter  {


    private LifecycleHandler lifecycleHandler;
    private ControlView controlView;
    private ConnectRepository connectingRepository;

    private String[] stringsForSettings;
    private String[] stringsToSettings;


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

    public void setStringsPreferences(String[] stringsForPreferences){
        stringsForSettings = stringsForPreferences;
        Observable.just(stringsForSettings)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(settings -> connectingRepository.setPreferences(settings),
                        throwable -> controlView.showLoadingError());
    }

    public void onNextWaitFragment(String[] stringsToPreferences){
        stringsToSettings = stringsToPreferences;
        setStringsPreferences(stringsToSettings);

        controlView.nextWaitFragment();
    }

}
