package com.example.work.loto.FirstAction.Repository;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.example.work.loto.FirstAction.Repository.Retrofit.ApiFactory;
import com.example.work.loto.FirstAction.Repository.Retrofit.SettingsObjects.PlayObject;

import java.util.List;

import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;


public class ConnectingRepository implements ConnectRepository {

    private static SharedPreferences sharedPreferences;
    private static String[] stringsPreferences;

    public ConnectingRepository(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }
    @Override
    @NonNull
    public Observable<String[]> getPreferences() {
        return PreferenceObject
                .getPreferenceObject(sharedPreferences)
                .compose(RxUtils.async());
    }
    @Override
    public void setPreferences(String[] preferences){
        stringsPreferences = preferences;
        PreferenceObject.setPreferenceObject(sharedPreferences, stringsPreferences);
    }
    @Override
    public Observable<List<PlayObject>> startGame(){
        return ApiFactory
                .getRetrofitService()
                .getGame(PreferenceObject.getStartingObject())
                .compose(RxUtils.async());
    }
}