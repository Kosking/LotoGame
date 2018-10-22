package initialAction.repository;

import my.game.loto.initialAction.repository.InitialPreference;
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerId;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;

public class MockInitialObject implements InitialPreference {

    private String playerId;

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    @Override
    public String getPlayerId() {
        return playerId;
    }

    @Override
    public PlayerId getPlayerIdObject(String playerId) {
        return null;
    }

    @Override
    public void setNewPlayerData(NewPlayerData playerData) {
    }

    @Override
    public void setNamePlayer(String[] playerSettings) {

    }

    @Override
    public void setFullGameObject(FullGameObject fullGameObject) {

    }

    @Override
    public void setPrimaryData(PrimaryData primaryData) {

    }
}
