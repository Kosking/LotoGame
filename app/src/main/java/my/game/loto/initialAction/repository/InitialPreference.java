package my.game.loto.initialAction.repository;

import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerSettings;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerId;

public interface InitialPreference {


    String getPlayerId();
    PlayerId getPlayerIdObject(String playerId);
    void saveIdPlayer(NewPlayerData playerData);
    NewPlayerSettings getPlayerSettings(String[] namePlayer);
}
