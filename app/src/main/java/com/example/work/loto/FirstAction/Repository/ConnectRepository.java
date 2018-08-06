package com.example.work.loto.FirstAction.Repository;

import com.example.work.loto.FirstAction.Repository.Retrofit.SettingsObjects.PlayObject;

import java.util.List;

import rx.Observable;

public interface ConnectRepository {

    Observable<String[]> getPreferences();
    void setPreferences(String[] preferences);
    Observable<List<PlayObject>> startGame();
}
