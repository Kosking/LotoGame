package my.game.loto.firstAction.repository;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

import my.game.loto.firstAction.retrofit.SettingsObjects.StartingObject;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;

public class PreferenceObject implements Preferences {

    //TODO final must is not value settings
    private static final String SPEED = "slow";
    private static final String MODE_CARDS = "long";
    private static final String MODE_ROOM = "open";
    private static final String QUANTITY_PLAYERS = "two";
    private static final String RATE = "100";

    private static final String PLAYER_ID = "thisPlayerId";
    private static String playerId;

    private String[] getStringsPreferences;
    private String[] setStringsPreferences;
    private static String[] stringsPreferences;
    private static String[] setStringsStartingObject;

    private static SharedPreferences sharedPreferences;


    private static String[] stringsSettings;

    public PreferenceObject(SharedPreferences mySharedPreferences){
        sharedPreferences = mySharedPreferences;
    }

    //TODO Start StartGameActivity check token
    @Override
    public String getToken() {
        String token = new String("SSS");
        return token;
    }

    @NonNull
    @Override
    public Observable<String[]> getPreferences() {
        return getPreferenceObject()
                .compose(RxUtils.async());
    }
    @Override
    public void setPreferences(String[] preferences){
        stringsSettings = preferences;
        setStringsPreferences(stringsSettings);
        setPreferenceObject();
    }

    //TODO rate should is retrofit field
    @NonNull
    private Observable<String[]> getPreferenceObject(){
        getStringsPreferences = new String[5];
        getStringsPreferences[0] = sharedPreferences.getString(SPEED, "slow");
        getStringsPreferences[1] = sharedPreferences.getString(MODE_CARDS, "short");
        getStringsPreferences[2] = sharedPreferences.getString(MODE_ROOM, "open");
        getStringsPreferences[3] = sharedPreferences.getString(QUANTITY_PLAYERS, "two");
        getStringsPreferences[4] = sharedPreferences.getString(RATE, "100");
        return Observable.just(getStringsPreferences);
    }

    private void setPreferenceObject(){
        setStringsPreferences = getStringsPreferences();
        Editor editor = sharedPreferences.edit();
        editor.putString(SPEED, setStringsPreferences[0]);
        editor.putString(MODE_CARDS, setStringsPreferences[1]);
        editor.putString(MODE_ROOM, setStringsPreferences[2]);
        editor.putString(QUANTITY_PLAYERS, setStringsPreferences[3]);
        editor.putString(RATE, setStringsPreferences[4]);
        editor.apply();
    }
    @Override
    public StartingObject getStartingObject(){
        setStringsStartingObject = getStringsPreferences();
        playerId = sharedPreferences.getString(PLAYER_ID, "");
        StartingObject startingObject = new StartingObject();
        startingObject.setStringsSettings(setStringsStartingObject);
        startingObject.setId(playerId);
        return startingObject;
    }

    //TODO Del, its for tests
    @Override
    public void setIdStartingObject(String idPlayer){
        String playersId = idPlayer;
        Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_ID, playersId);
        editor.apply();
    }
    private static String[] getStringsPreferences() {
        return stringsPreferences;
    }

    //TODO Del public -> private, its for tests
    @Override
    public void setStringsPreferences(String[] myStringsPreferences) {
        stringsPreferences = myStringsPreferences;;
    }
}
