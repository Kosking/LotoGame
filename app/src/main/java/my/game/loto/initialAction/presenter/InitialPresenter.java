package my.game.loto.initialAction.presenter;

import my.game.loto.R;
import my.game.loto.initialAction.repository.InitialProvider;
import my.game.loto.initialAction.screens.InitialView;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerToken;
import ru.arturvasilov.rxloader.LifecycleHandler;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InitialPresenter {

    InitialView initialView;
    LifecycleHandler lifecycleHandler;

    public InitialPresenter(InitialView initialActivity, LifecycleHandler lifecycleHandler) {
       this.initialView = initialActivity;
       this.lifecycleHandler = lifecycleHandler;
    }

    public void upAction() {
        String playerId = InitialProvider.provideInitialObject().getPlayerId();
        if (playerId.equals("")){
            initialView.nextWelcomeFragment();
        } else {
            InitialProvider
                    .providePreparatoryRepository()
                    .getPlayerGameToken(playerId)
                    .compose(lifecycleHandler.load(R.id.idPlayerRetrofit))
                    .subscribe(this::nextActivity,
                            throwable -> initialView.showLoadingError());;
        }
    }

    private void nextActivity(PlayerToken playerToken) {
        String myPlayerToken = playerToken.getToken();
        if (myPlayerToken.equals("true")){
            InitialProvider
                    .providePreparatoryRepository()
                    .getPlayData()
                    .compose(lifecycleHandler.load(R.id.idPlayerRetrofit))
                    .subscribe(fullGameObject -> initialView.nextGameActivity(fullGameObject),
                            throwable -> initialView.showLoadingError());;
        } else {
            InitialProvider
                    .providePreparatoryRepository()
                    .getPrimaryData()
                    .compose(lifecycleHandler.load(R.id.idPlayerRetrofit))
                    .subscribe(primaryData -> initialView.nextChoiceActivity(primaryData),
                            throwable -> initialView.showLoadingError());;
        }
    }

    public void uploadingNewPlayerId(String[] playerSettings) {
        InitialProvider
                .providePreparatoryRepository()
                .createNewPlayer(playerSettings)
                .compose(lifecycleHandler.load(R.id.idPlayerRetrofit))
                .subscribe(this::toFreshChoiceActivity,
                        throwable -> initialView.showLoadingError());;
    }

    private void toFreshChoiceActivity(NewPlayerData newPlayerData) {
        NewPlayerData myNewPlayerData = newPlayerData;
        Observable.just(myNewPlayerData)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(lifecycleHandler.load(R.id.setPreferences))
                .subscribe(playerData -> InitialProvider
                                .provideInitialObject()
                                .saveIdPlayer(playerData),
                        throwable -> initialView.showLoadingError());
        initialView.freshChoiceActivity(myNewPlayerData);
    }
}
