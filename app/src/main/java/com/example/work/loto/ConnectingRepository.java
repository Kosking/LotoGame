package com.example.work.loto;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;


public class ConnectingRepository implements ConnectRepository {

    private static SharedPreferences sharedPreferences;
    private static String stringsPreferences[];

    public ConnectingRepository(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    public Observable<String[]> getPreferences() {
        return PreferenceObject
                .getPreferenceObject(sharedPreferences)
                .compose(RxUtils.async());
    }

    public void setPreferences(String preferences[]){
        stringsPreferences = preferences;
        PreferenceObject.setPreferenceObject(sharedPreferences, stringsPreferences);
    }

}