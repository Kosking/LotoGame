package my.game.loto.choiceAction.presenter;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

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


    public ChoicePresenter(@NonNull ControlView controlView,
                           @NonNull LifecycleHandler lifecycleHandler) {
        this.controlView = controlView;
        this.lifecycleHandler = lifecycleHandler;
    }

    public void getStartData(){
        String playerName = RepositoryProvider.providePreferenceObject().getPlayerName();
        controlView.setStartData(playerName);
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
        Observable.just(stringsForPreferences)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(lifecycleHandler.load(R.id.setPreferences))
                .subscribe(stringsSettings -> RepositoryProvider
                                .providePreferenceObject()
                                .setPreferences(stringsSettings),
                        throwable -> controlView.showLoadingError());
    }

    public void onNextWaitFragment(String[] stringsToPreferences){
        setStringsPreferences(stringsToPreferences);
        RepositoryProvider
                .provideConnectingRepository()
                .startGame()
                .repeatWhen(objectObservable -> objectObservable.delay(1, TimeUnit.SECONDS).take(60))
                .takeUntil(start-> start.get(0).getStart().equals("true"))
                .doOnSubscribe(controlView::nextWaitFragment)
                .compose(lifecycleHandler.load(R.id.playObjectRetrofit))
                .subscribe(playObject -> controlView.nextSecondActivity(playObject),
                        throwable -> controlView.showLoadingError());
    }

}
