package my.game.loto.initialAction.repository;

import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerSettings;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerId;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;

public interface InitialPreference {


    String getPlayerId();
    PlayerId getPlayerIdObject(String playerId);
    void setNewPlayerData(NewPlayerData playerData);
    NewPlayerSettings getPlayerSettingsObject(String[] namePlayer);
    String getTestToken();
    void setTestToken(String myToken);
    void setNamePlayer(String[] playerSettings);
    void setFullGameObject(FullGameObject fullGameObject);
    void setPrimaryData(PrimaryData primaryData);
}
