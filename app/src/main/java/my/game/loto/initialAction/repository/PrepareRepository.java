package my.game.loto.initialAction.repository;

import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerToken;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
import rx.Observable;

public interface PrepareRepository {

    void setPlayerIdObject(String playerId);
    Observable<PlayerToken> getPlayerGameToken();
    Observable<FullGameObject> getPlayData();
    Observable<PrimaryData> getPrimaryData();
    Observable<NewPlayerData> createNewPlayer(String[] newPlayerSettings);
}
