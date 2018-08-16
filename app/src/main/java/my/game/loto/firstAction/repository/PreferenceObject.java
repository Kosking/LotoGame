package my.game.loto.firstAction.repository;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import my.game.loto.AppDelegate;
import my.game.loto.firstAction.retrofit.settingsObjects.StartingObject;
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
    private static String[] setStringsStartingObject;

    private static SharedPreferences sharedPreferences;
    //TODO del, for test
    private String token;


    private static volatile String[] stringsSettings;

    PreferenceObject(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext());;
    }

    //TODO del, for test Retrofit (before Start ChoiceActivity check token)
    @Override
    public String getTestToken() {
        return token;
    }
    //TODO del, for test Retrofit
    @Override
    public void setTestToken(String myToken) {
        token = myToken;
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
    public StartingObject getStartingObject(){
        setStringsStartingObject = stringsSettings;
        playerId = sharedPreferences.getString(PLAYER_ID, "");
        return  new StartingObject(playerId, setStringsStartingObject);
        /*JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stringsSettings", Arrays.toString(setStringsStartingObject));
        jsonObject.addProperty("playerId", Arrays.toString(setStringsStartingObject));
        return jsonObject;*/
    }

    //TODO Del, its for tests
    @Override
    public void setIdStartingObject(String idPlayer){
        Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_ID, idPlayer);
        editor.apply();
    }
}
