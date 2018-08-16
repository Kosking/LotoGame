package my.game.loto.initialAction;

import my.game.loto.R;
import ru.arturvasilov.rxloader.LifecycleHandler;

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
                    .subscribe(playerToken -> nextActivity(playerToken),
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
}
