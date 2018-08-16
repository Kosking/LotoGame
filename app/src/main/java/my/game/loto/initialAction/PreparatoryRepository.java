package my.game.loto.initialAction;

import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;

public class PreparatoryRepository implements PrepareRepository {

    //TODO volatile or not
    private volatile PlayerId playerIdObject;
    @Override
    public Observable<PlayerToken> getPlayerGameToken(String playerId) {
        playerIdObject = InitialProvider.provideInitialObject().getPlayerIdObject(playerId);
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
}
