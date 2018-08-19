package initialAction.repository;

import my.game.loto.initialAction.repository.InitialPreference;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerSettings;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerId;

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
    public void saveIdPlayer(NewPlayerData playerData) {
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
    public void saveNamePlayer(String[] playerSettings) {
    }
}
