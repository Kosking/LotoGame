package com.example.work.loto;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;

import ru.arturvasilov.rxloader.LifecycleHandler;

public class ChoicePresenter  {


    private LifecycleHandler LifecycleHandler;
    private MainActivity mainActivity;



    public ChoicePresenter(@NonNull MainActivity mainActivity,
                           @NonNull LifecycleHandler lifecycleHandler) {
        this.mainActivity = mainActivity;
        this.LifecycleHandler = lifecycleHandler;
    }

    /*public void init() {
        String token = PreferenceObject.getToken();
        if (!TextUtils.isEmpty(token)) {
            mainActivity.openPlayingScreen();
        }
    }*/

    public void settingsInit(){
        Hawk.init(mainActivity).build();

    }

    public void onNextChoiceFragment(){
        RepositoryProvider.provideGithubRepository()
                .getPreferences()
                .compose(LifecycleHandler.load(R.id.preferences))
                .subscribe(preferencesObject -> mainActivity.nextChoiceFragment(preferencesObject),
                        throwable -> mainActivity.showLoadingError());
    }

    public void onNextWaitFragment(){
        mainActivity.nextWaitFragment();
    }

}
