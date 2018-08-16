package my.game.loto.initialAction;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import my.game.loto.AppDelegate;

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

}
