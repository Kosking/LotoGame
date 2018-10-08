package my.game.loto.initialAction.repository;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import java.util.List;

import my.game.loto.AppDelegate;
import my.game.loto.initialAction.repository.room.InitialDao;
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerSettings;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerId;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
import my.game.loto.roomDatabase.AppDatabase;

public class InitialObject implements InitialPreference {

    private static final String PLAYER_ID = "thisPlayerId";
    private static final String PLAYER_NAME = "thisPlayerId";
    private static final String CARDS = "playersCards";

    private SharedPreferences sharedPreferences;
    private InitialDao initialDao;

    //TODO del, for test
    private String testToken;

    InitialObject(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext());
        AppDatabase database = AppDelegate.getDatabase();
        initialDao = database.initialDao();
    }

    @Override
    public String getPlayerId(){
        return sharedPreferences.getString(PLAYER_ID, "");
    }

    @Override
    public PlayerId getPlayerIdObject(String playerId) {
        return new PlayerId(playerId);
    }

    @Override
    public void setNewPlayerData(NewPlayerData newPlayerData) {
        Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_ID, newPlayerData.getId());
        List<String> allFullCards = newPlayerData.getAllFullCards();
        for(int i = 0; i < allFullCards.size(); i++){
            editor.putString(CARDS + i, allFullCards.get(i));
        }
        editor.apply();
        PrimaryData primaryData = new PrimaryData(0, newPlayerData.getPlayerMoney(), newPlayerData.getPlayerDiamonds());
        setPrimaryData(primaryData);
    }

    @Override
    public void setNamePlayer(String[] playerSettings) {
        Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_NAME, playerSettings[1]);
        editor.apply();
    }

    @Override
    public NewPlayerSettings getPlayerSettingsObject(String[] playerSettings) {
        //image[0] and namePlayer[1]
        return new NewPlayerSettings(playerSettings[0], playerSettings[1]);
    }

    @Override
    public void setFullGameObject(FullGameObject fullGameObject){
        initialDao.setFullGameObject(fullGameObject);
    }

    @Override
    public void setPrimaryData(PrimaryData primaryData) {
        initialDao.setPrimaryData(primaryData);
    }

    //TODO del, for test Retrofit (before Start ChoiceActivity check token)
    @Override
    public String getTestToken() { return testToken; }
    //TODO del, for test Retrofit
    @Override
    public void setTestToken(String myToken) {
        testToken = myToken;
    }
}
