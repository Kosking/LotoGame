package my.game.loto.choiceAction.presenter;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import my.game.loto.R;
import my.game.loto.choiceAction.repository.RepositoryProvider;
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import my.game.loto.choiceAction.screens.ControlView;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChoicePresenter {
    private LifecycleHandler lifecycleHandler;
    private ControlView controlView;

    public ChoicePresenter(@NonNull ControlView controlView,
                           @NonNull LifecycleHandler lifecycleHandler) {
        this.controlView = controlView;
        this.lifecycleHandler = lifecycleHandler;
    }

    public void startData() {
        RepositoryProvider
                .provideChoiceObject()
                .getStartObject()
                .compose(RxUtils.async())
                .compose(lifecycleHandler.load(R.id.getPreferences))
                .subscribe(startObject -> controlView.setFragment(startObject),
                        throwable -> controlView.showLoadingError());
    }

    public void onNextChoiceFragment() {
        RepositoryProvider
                .provideChoiceObject()
                .getPreferences()
                .compose(RxUtils.async())
                .compose(lifecycleHandler.load(R.id.getPreferences))
                .subscribe(preferences -> controlView.nextChoiceFragment(preferences),
                        throwable -> controlView.showLoadingError());
    }

    public void onNextWaitFragment(String[] stringsToPreferences) {
        setStringsPreferences(stringsToPreferences);
        RepositoryProvider
                .provideConnectingRepository()
                .startGame()
                .repeatWhen(objectObservable -> objectObservable.delay(1, TimeUnit.SECONDS).take(60))
                .takeUntil(start -> start.get(0).getStart().equals("true"))
                .doOnSubscribe(controlView::nextWaitFragment)
                .compose(lifecycleHandler.load(R.id.playObjectRetrofit))
                .subscribe(this::nextGameActivity,
                        throwable -> controlView.showLoadingError());
    }

    private void setStringsPreferences(String[] stringsForPreferences) {
        Observable.just(stringsForPreferences)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(lifecycleHandler.load(R.id.setPreferences))
                .subscribe(stringsSettings -> RepositoryProvider
                                .provideChoiceObject()
                                .setPreferences(stringsSettings),
                        throwable -> controlView.showLoadingError());
    }

    private void nextGameActivity(List<PlayObject> listPlayObjects) {
        Observable.just(listPlayObjects)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(lifecycleHandler.load(R.id.setPlayObjects))
                .subscribe(playObjects -> RepositoryProvider
                                .provideChoiceObject()
                                .setListPlayObjects(playObjects),
                        throwable -> controlView.showLoadingError());
        controlView.nextSecondAction();
    }
}
