package my.game.loto.initialAction;

import rx.Observable;

public interface PrepareRepository {

    Observable<PlayerToken> getPlayerGameToken(String playerId);
    Observable<FullGameObject> getPlayData();
    Observable<PrimaryData> getPrimaryData();
}
