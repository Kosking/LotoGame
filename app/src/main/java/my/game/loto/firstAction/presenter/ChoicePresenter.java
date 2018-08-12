package my.game.loto.firstAction.presenter;

import android.support.annotation.NonNull;

import my.game.loto.R;
import my.game.loto.firstAction.repository.RepositoryProvider;
import my.game.loto.firstAction.screens.ControlView;
import ru.arturvasilov.rxloader.LifecycleHandler;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChoicePresenter  {


    private LifecycleHandler lifecycleHandler;
    private ControlView controlView;


    private String[] stringsForSettings;
    private String[] stringsToSettings;


    public ChoicePresenter(@NonNull ControlView controlView,
                           @NonNull LifecycleHandler lifecycleHandler) {
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
        RepositoryProvider
                .providePreferenceObject()
                .getPreferences()
                .compose(lifecycleHandler.load(R.id.getPreferences))
                .subscribe(preferencesObject -> controlView.nextChoiceFragment(preferencesObject),
                        throwable -> controlView.showLoadingError());
    }

    private void setStringsPreferences(String[] stringsForPreferences){
        stringsForSettings = stringsForPreferences;
        Observable.just(stringsForSettings)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(lifecycleHandler.load(R.id.setPreferences))
                .subscribe(stringsSettings -> RepositoryProvider
                                .providePreferenceObject()
                                .setPreferences(stringsSettings),
                        throwable -> controlView.showLoadingError());
    }

    public void onNextWaitFragment(String[] stringsToPreferences){
        stringsToSettings = stringsToPreferences;
        setStringsPreferences(stringsToSettings);
        RepositoryProvider
                .provideConnectingRepository()
                .startGame()
                .doOnSubscribe(controlView::nextWaitFragment)
                .compose(lifecycleHandler.load(R.id.retrofit))
                .subscribe(playObject -> controlView.nextToSecondActivity(playObject),
                        throwable -> controlView.showLoadingError());
    }

}
