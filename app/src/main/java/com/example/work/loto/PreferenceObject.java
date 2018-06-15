package com.example.work.loto;

import android.support.annotation.NonNull;

import com.orhanobut.hawk.Hawk;

import rx.Observable;

public class PreferenceObject {

    private static final String SPEED = "slow";
    private static final String MODE_CARDS = "long";
    private static final String MODE_ROOM = "open";
    private static final String QUANTITY_PLAYERS = "two";
    private static final String RATE = "100";

    private static String[] getPreferences;
    private static String[] setPreferences;

    private PreferenceObject(){

    }

    //TODO Start MainActivity check token
    public static String getToken() {
        String token = new String("SSS");
        return token;
    }

    //TODO rate should is retrofit field
    @NonNull
    public static Observable<String[]> getPreferenceObject(){
        getPreferences = new String[5];
        getPreferences[0] = Hawk.get(SPEED, "");
        getPreferences[1] = Hawk.get(MODE_CARDS, "");
        getPreferences[2] = Hawk.get(MODE_ROOM, "");
        getPreferences[3] = Hawk.get(QUANTITY_PLAYERS, "");
        getPreferences[4] = Hawk.get(RATE, "");
        return Observable.just(getPreferences);
    }

    public static void setPreferenceObject(String[] preferenceObject){
        setPreferences = preferenceObject;
        Hawk.put(SPEED, setPreferences[0]);
        Hawk.put(MODE_CARDS, setPreferences[1]);
        Hawk.put(MODE_ROOM, setPreferences[2]);
        Hawk.put(QUANTITY_PLAYERS, setPreferences[3]);
        Hawk.put(RATE, setPreferences[4]);

    }

}
