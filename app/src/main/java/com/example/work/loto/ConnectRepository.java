package com.example.work.loto;

import rx.Observable;

public interface ConnectRepository {

    Observable<String[]> getPreferences();
    void setPreferences(String preferences[]);
}
