package com.example.work.loto;

import android.support.annotation.NonNull;

import rx.Observable;


public interface GithubRepository {


    @NonNull
    Observable<String[]> getPreferences();
}
