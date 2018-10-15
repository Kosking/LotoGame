package initialAction.repository;

import my.game.loto.initialAction.repository.PrepareRepository;
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerToken;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
import rx.Observable;

public class MockPreparatoryRepository implements PrepareRepository {

    private PlayerToken playerToken;
    private static final String tokenFalse = "false";

    @Override
    public void setPlayerIdObject(String playerId) {
    }

    @Override
    public Observable<PlayerToken> getPlayerGameToken() {
        return Observable.just(playerToken);
    }

    @Override
    public Observable<FullGameObject> getPlayData() {
        return Observable.just(null);
    }

    @Override
    public Observable<PrimaryData> getPrimaryData() {
        return Observable.just(null);
    }

    @Override
    public Observable<NewPlayerData> createNewPlayer(String[] newPlayerSettings) {
        return Observable.just(null);
    }
    @Override
    public void setPlayerToken(String playerToken){
        if (playerToken.equals("true")){
            this.playerToken.setToken(playerToken);
        } else {
            this.playerToken.setToken(tokenFalse);
        }
    }
}
