package my.game.loto.initialAction.presenter;

import my.game.loto.R;
import my.game.loto.initialAction.repository.InitialProvider;
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerToken;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
import my.game.loto.initialAction.screens.InitialView;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InitialPresenter {

    private InitialView initialView;
    private LifecycleHandler lifecycleHandler;

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
                    .compose(RxUtils.async())
                    .compose(lifecycleHandler.load(R.id.idPlayerRetrofit))
                    .subscribe(this::nextActivity,
                            throwable -> initialView.showLoadingError());;
        }
    }

    private void nextActivity(PlayerToken playerToken) {
        if (playerToken.getToken().equals("true")){
            InitialProvider
                    .providePreparatoryRepository()
                    .getPlayData()
                    .compose(RxUtils.async())
                    .compose(lifecycleHandler.load(R.id.playerTokenRetrofit))
                    .subscribe(this::nextGameActivity,
                            throwable -> initialView.showLoadingError());;
        } else {
            InitialProvider
                    .providePreparatoryRepository()
                    .getPrimaryData()
                    .compose(RxUtils.async())
                    .compose(lifecycleHandler.load(R.id.primaryDataRetrofit))
                    .subscribe(this::nextChoiceActivity,
                            throwable -> initialView.showLoadingError());
        }
    }

    private void nextGameActivity(FullGameObject myFullGameObject){
        Observable.just(myFullGameObject)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(lifecycleHandler.load(R.id.setFullGameObject))
                .subscribe(fullGameObject -> InitialProvider
                                .provideInitialObject()
                                .setFullGameObject(fullGameObject),
                        throwable -> initialView.showLoadingError(),
                        () -> initialView.nextGameActivity());
    }

    private void  nextChoiceActivity(PrimaryData myPrimaryData){
        Observable.just(myPrimaryData)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(lifecycleHandler.load(R.id.setPrimaryData))
                .subscribe(primaryData -> InitialProvider
                                .provideInitialObject()
                                .setPrimaryData(primaryData),
                        throwable -> initialView.showLoadingError(),
                        () -> initialView.nextChoiceActivity());
    }

    public void downloadingNewPlayerId(String[] playerSettings) {
        Observable.just(playerSettings)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(lifecycleHandler.load(R.id.setNameNewPlayer))
                .subscribe(myPlayerSettings -> InitialProvider
                                .provideInitialObject()
                                .setNamePlayer(myPlayerSettings),
                        throwable -> initialView.showLoadingError());
        InitialProvider
                .providePreparatoryRepository()
                .createNewPlayer(playerSettings)
                .compose(RxUtils.async())
                .compose(lifecycleHandler.load(R.id.dataNewPlayer))
                .subscribe(this::toFreshChoiceActivity,
                        throwable -> initialView.showLoadingError());
    }

    private void toFreshChoiceActivity(NewPlayerData newPlayerData) {
        Observable.just(newPlayerData)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(lifecycleHandler.load(R.id.setIdNewPlayer))
                .subscribe(playerData -> InitialProvider
                                .provideInitialObject()
                                .setNewPlayerData(playerData),
                        throwable -> initialView.showLoadingError(),
                        () -> initialView.nextChoiceActivity());
    }
}
