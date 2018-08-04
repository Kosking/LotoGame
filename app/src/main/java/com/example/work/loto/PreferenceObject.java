package com.example.work.loto;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

import rx.Observable;

public class PreferenceObject {

    //TODO final must is not value settings
    private static final String SPEED = "slow";
    private static final String MODE_CARDS = "long";
    private static final String MODE_ROOM = "open";
    private static final String QUANTITY_PLAYERS = "two";
    private static final String RATE = "100";

    private static String[] getStringsPreferences;
    private static String[] setStringsPreferences;

    private static SharedPreferences sharedPreferencesGet;
    private static SharedPreferences sharedPreferencesSet;

    private PreferenceObject(){

    }

    //TODO Start MainActivity check token
    public static String getToken() {
        String token = new String("SSS");
        return token;
    }

    //TODO rate should is retrofit field
    @NonNull
    public static Observable<String[]> getPreferenceObject(SharedPreferences sharedPreferences){
        sharedPreferencesGet = sharedPreferences;
        getStringsPreferences = new String[5];
        getStringsPreferences[0] = sharedPreferencesGet.getString(SPEED, "slow");
        getStringsPreferences[1] = sharedPreferencesGet.getString(MODE_CARDS, "short");
        getStringsPreferences[2] = sharedPreferencesGet.getString(MODE_ROOM, "open");
        getStringsPreferences[3] = sharedPreferencesGet.getString(QUANTITY_PLAYERS, "two");
        getStringsPreferences[4] = sharedPreferencesGet.getString(RATE, "100");
        return Observable.just(getStringsPreferences);
    }

    public static void setPreferenceObject(SharedPreferences sharedPreferences, String[] stringsPreferences){
        sharedPreferencesSet = sharedPreferences;
        setStringsPreferences = stringsPreferences;
        Editor editor = sharedPreferencesSet.edit();
        editor.putString(SPEED, setStringsPreferences[0]);
        editor.putString(MODE_CARDS, setStringsPreferences[1]);
        editor.putString(MODE_ROOM, setStringsPreferences[2]);
        editor.putString(QUANTITY_PLAYERS, setStringsPreferences[3]);
        editor.putString(RATE, setStringsPreferences[4]);
        editor.apply();
    }

}
