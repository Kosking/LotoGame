package my.game.loto.initialAction.presenter;

import my.game.loto.R;
import my.game.loto.initialAction.repository.InitialProvider;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerToken;
import my.game.loto.initialAction.screens.InitialView;
import ru.arturvasilov.rxloader.LifecycleHandler;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InitialPresenter {

    private InitialView initialView;
    private LifecycleHandler lifecycleHandler;

    private volatile String[] myPlayerSettings;
    private volatile NewPlayerData newPlayerData;

    public InitialPresenter(InitialView initialActivity, LifecycleHandler lifecycleHandler) {
       this.initialView = initialActivity;
       this.lifecycleHandler = lifecycleHandler;
    }

    public void upAction() {
        String playerId = InitialProvider.provideInitialObject().getPlayerId();
        if (playerId.equals("")){
            initialView.nextWelcomeFragment();
        } else {
            InitialProvider.providePreparatoryRepository().setPlayerIdObject(playerId);
            InitialProvider
                    .providePreparatoryRepository()
                    .getPlayerGameToken()
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
                    .compose(lifecycleHandler.load(R.id.playerTokenRetrofit))
                    .subscribe(fullGameObject -> initialView.nextGameActivity(fullGameObject),
                            throwable -> initialView.showLoadingError());;
        } else {
            InitialProvider
                    .providePreparatoryRepository()
                    .getPrimaryData()
                    .compose(lifecycleHandler.load(R.id.primaryDataRetrofit))
                    .subscribe(primaryData -> initialView.nextChoiceActivity(primaryData),
                            throwable -> initialView.showLoadingError());;
        }
    }

    public void uploadingNewPlayerId(String[] playerSettings) {
        myPlayerSettings = playerSettings;
        Observable.just(myPlayerSettings)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(lifecycleHandler.load(R.id.setNameNewPlayer))
                .subscribe(myPlayerSettings -> InitialProvider
                                .provideInitialObject()
                                .saveNamePlayer(myPlayerSettings),
                        throwable -> initialView.showLoadingError());
        InitialProvider
                .providePreparatoryRepository()
                .createNewPlayer(myPlayerSettings)
                .compose(lifecycleHandler.load(R.id.dataNewPlayer))
                .subscribe(this::toFreshChoiceActivity,
                        throwable -> initialView.showLoadingError());;
    }

    private void toFreshChoiceActivity(NewPlayerData newPlayerData) {
        this.newPlayerData = newPlayerData;
        Observable.just(this.newPlayerData)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(lifecycleHandler.load(R.id.setIdNewPlayer))
                .subscribe(playerData -> InitialProvider
                                .provideInitialObject()
                                .saveIdPlayer(playerData),
                        throwable -> initialView.showLoadingError());
        initialView.freshChoiceActivity(this.newPlayerData);
    }
}
