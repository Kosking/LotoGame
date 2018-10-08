package my.game.loto.choiceAction.repository;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.util.List;

import my.game.loto.AppDelegate;
import my.game.loto.choiceAction.repository.room.ChoiceDao;
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
import my.game.loto.roomDatabase.AppDatabase;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;

public class ChoiceObject implements ChoicePreference {

    //TODO final must is not value settings
    private static final String SPEED = "slow";
    private static final String MODE_CARDS = "long";
    private static final String MODE_ROOM = "open";
    private static final String QUANTITY_PLAYERS = "two";
    private static final String RATE = "100";
    private static final String PLAYER_NAME = "thisPlayerId";

    private static final String PLAYER_ID = "thisPlayerId";

    private String[] getStringsPreferences;
    private String[] setStringsPreferences;
    private String[] setStringsStartingObject;

    private SharedPreferences sharedPreferences;
    private ChoiceDao choiceDao;
    //TODO del, for test
    private String testToken;

    private static volatile String[] stringsSettings;

    ChoiceObject() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext());
        AppDatabase database = AppDelegate.getDatabase();
        choiceDao = database.choiceDao();
    }

    @NonNull
    @Override
    public Observable<String[]> getPreferences() {
        return getSettings()
                .compose(RxUtils.async());
    }

    @Override
    public void setPreferences(String[] preferences) {
        stringsSettings = preferences;
        setSettings();
    }

    //TODO rate should is retrofit field
    @NonNull
    private Observable<String[]> getSettings() {
        getStringsPreferences = new String[5];
        getStringsPreferences[0] = sharedPreferences.getString(SPEED, "slow");
        getStringsPreferences[1] = sharedPreferences.getString(MODE_CARDS, "short");
        getStringsPreferences[2] = sharedPreferences.getString(MODE_ROOM, "open");
        getStringsPreferences[3] = sharedPreferences.getString(QUANTITY_PLAYERS, "two");
        getStringsPreferences[4] = sharedPreferences.getString(RATE, "100");
        return Observable.just(getStringsPreferences);
    }

    private void setSettings() {
        setStringsPreferences = stringsSettings;
        Editor editor = sharedPreferences.edit();
        editor.putString(SPEED, setStringsPreferences[0]);
        editor.putString(MODE_CARDS, setStringsPreferences[1]);
        editor.putString(MODE_ROOM, setStringsPreferences[2]);
        editor.putString(QUANTITY_PLAYERS, setStringsPreferences[3]);
        editor.putString(RATE, setStringsPreferences[4]);
        editor.apply();
    }

    @Override
    public String getPlayerName() {
        return sharedPreferences.getString(PLAYER_NAME, "root");
    }

    @Override
    public PrimaryData getPrimaryData() {
        return choiceDao.getPrimaryData();
    }

    @Override
    public StartingObject getStartingObject() {
        setStringsStartingObject = stringsSettings;
        String playerId = sharedPreferences.getString(PLAYER_ID, "");
        return new StartingObject(playerId, setStringsStartingObject);
    }

    @Override
    public void setListPlayObjects(List<PlayObject> listPlayObjects) {
        choiceDao.insertPlayObjects(listPlayObjects);
    }

    //TODO del, for test Retrofit
    @Override
    public void setTestToken(String myToken) {
        testToken = myToken;
    }

    @Override
    public String getTestToken() {
        return testToken;
    }

    //TODO Del, its for tests
    @Override
    public void setIdStartingObject(String idPlayer) {
        Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_ID, idPlayer);
        editor.apply();
    }

    //TODO Del, its for tests
    @Override
    public void setPlayerName(String playerName) {
        Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_NAME, playerName);
        editor.apply();
    }
}
