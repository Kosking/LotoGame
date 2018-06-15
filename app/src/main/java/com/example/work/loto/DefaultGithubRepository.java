package com.example.work.loto;

import android.support.annotation.NonNull;

import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;


public class DefaultGithubRepository implements GithubRepository {

    @NonNull
    @Override
    public Observable<String[]> getPreferences() {
        return PreferenceObject
                .getPreferenceObject()
                .compose(RxUtils.async());
    }

}