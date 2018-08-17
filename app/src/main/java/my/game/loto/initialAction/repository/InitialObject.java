package my.game.loto.initialAction.repository;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import my.game.loto.AppDelegate;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerSettings;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerId;

public class InitialObject implements InitialPreference {

    private static final String PLAYER_ID = "thisPlayerId";
    private static String playerId;

    private static SharedPreferences sharedPreferences;

    InitialObject(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext());;
    }

    public String getPlayerId(){
        return sharedPreferences.getString(PLAYER_ID, "");
    }

    @Override
    public PlayerId getPlayerIdObject(String playerId) {
        return new PlayerId(playerId);
    }

    @Override
    public void saveIdPlayer(NewPlayerData playerData) {
        Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_ID, playerData.getId());
        editor.apply();
    }

    @Override
    public NewPlayerSettings getPlayerSettings(String[] playerSettings) {
        //image[0] and namePlayer[1]
        return new NewPlayerSettings(playerSettings[0], playerSettings[1]);
    }

}
