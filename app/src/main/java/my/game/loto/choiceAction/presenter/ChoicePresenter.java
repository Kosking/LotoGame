package my.game.loto.choiceAction.presenter;

import android.support.annotation.NonNull;

import my.game.loto.R;
import my.game.loto.choiceAction.repository.RepositoryProvider;
import my.game.loto.choiceAction.screens.ControlView;
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

    public void getPlayerName(){
        String playerName = RepositoryProvider.providePreferenceObject().getPlayerName();
        controlView.setPlayerName(playerName);
    }


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
                .compose(lifecycleHandler.load(R.id.playObjectRetrofit))
                .subscribe(playObject -> controlView.nextSecondActivity(playObject),
                        throwable -> controlView.showLoadingError());
    }

}
