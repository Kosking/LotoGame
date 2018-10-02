package initialAction.repository;

import my.game.loto.initialAction.repository.InitialPreference;
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerSettings;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerId;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;

public class MockInitialObject implements InitialPreference {

    String playerId;

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
    public void setPlayerId(NewPlayerData playerData) {
        this.playerId = playerData.getId();
    }

    @Override
    public NewPlayerSettings getPlayerSettingsObject(String[] namePlayer) {
        return null;
    }

    @Override
    public String getTestToken() {
        return null;
    }

    @Override
    public void setTestToken(String myToken) {
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
