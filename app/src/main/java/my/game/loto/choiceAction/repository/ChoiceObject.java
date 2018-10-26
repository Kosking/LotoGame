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
import my.game.loto.room.AppDatabase;
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
    private static final String NUMBER_OF_PLAYERS = "numberOfPlayers";
    private static final String LIST_PLAY_TOKEN = "listPlayObject";

    private SharedPreferences sharedPreferences;
    private ChoiceDao choiceDao;

    ChoiceObject() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext());
        AppDatabase database = AppDelegate.getDatabase();
        choiceDao = database.choiceDao();
    }

    @NonNull
    @Override
    public Observable<StartObject> getStartObject() {
        return Observable.fromCallable(this::start);
    }

    @NonNull
    private StartObject start() {
        String playerName = sharedPreferences.getString(PLAYER_NAME, "root");
        PrimaryData primaryData = choiceDao.getPrimaryData();
        return new StartObject(playerName, primaryData);
    }

    @NonNull
    @Override
    public Observable<String[]> getPreferences() {
        return Observable.fromCallable(this::getSettings);
    }

    //TODO rate should is retrofit field
    @NonNull
    private String[] getSettings() {
        String[] getStringsPreferences = new String[5];
        getStringsPreferences[0] = sharedPreferences.getString(SPEED, "slow");
        getStringsPreferences[1] = sharedPreferences.getString(MODE_CARDS, "short");
        getStringsPreferences[2] = sharedPreferences.getString(MODE_ROOM, "open");
        getStringsPreferences[3] = sharedPreferences.getString(QUANTITY_PLAYERS, "two");
        getStringsPreferences[4] = sharedPreferences.getString(RATE, "100");
        return getStringsPreferences;
    }

    @Override
    public void setPreferences(String[] stringsPreferences) {
        Editor editor = sharedPreferences.edit();
        editor.putString(SPEED, stringsPreferences[0]);
        editor.putString(MODE_CARDS, stringsPreferences[1]);
        editor.putString(MODE_ROOM, stringsPreferences[2]);
        editor.putString(QUANTITY_PLAYERS, stringsPreferences[3]);
        editor.putString(RATE, stringsPreferences[4]);
        editor.apply();
    }

    @Override
    public StartingObject getStartingObject(String[] stringsPreferences) {
        String playerId = sharedPreferences.getString(PLAYER_ID, "");
        return new StartingObject(playerId, stringsPreferences);
    }

    @Override
    public void setListPlayObjects(List<PlayObject> listPlayObjects) {
        choiceDao.setPlayObjects(listPlayObjects);
        int numberOfPlayers = listPlayObjects.size();
        Editor editor = sharedPreferences.edit();
        editor.putString(LIST_PLAY_TOKEN, "true");
        editor.putInt(NUMBER_OF_PLAYERS, numberOfPlayers);
        editor.apply();
    }
}
