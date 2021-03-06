package my.game.loto.initialAction.repository;

import my.game.loto.initialAction.retrofit.InitialApi;
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerSettings;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerId;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerToken;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;

public class PreparatoryRepository implements PrepareRepository {

    private volatile PlayerId playerIdObject;

    @Override
    public void setPlayerIdObject(String playerId){
        playerIdObject = InitialProvider.provideInitialObject().getPlayerIdObject(playerId);
    }

    @Override
    public Observable<PlayerToken> getPlayerGameToken() {
        return InitialApi
                .getRetrofitService()
                .playerToken(playerIdObject)
                .compose(RxUtils.async());
    }

    @Override
    public Observable<FullGameObject> getPlayData() {
        return InitialApi
                .getRetrofitService()
                .getPlayData(playerIdObject)
                .compose(RxUtils.async());
    }

    @Override
    public Observable<PrimaryData> getPrimaryData() {
        return InitialApi
                .getRetrofitService()
                .getPrimaryData(playerIdObject)
                .compose(RxUtils.async());
    }

    @Override
    public Observable<NewPlayerData> createNewPlayer(String[] playerSettings) {
        return InitialApi
                .getRetrofitService()  //image[0] and namePlayer[1]
                .createNewPlayer(new NewPlayerSettings(playerSettings[0], playerSettings[1]))
                .compose(RxUtils.async());
    }
}
