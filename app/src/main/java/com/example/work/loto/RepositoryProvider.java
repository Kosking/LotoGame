package com.example.work.loto;

import android.support.annotation.NonNull;

/**
 * @author Artur Vasilov
 */
public final class RepositoryProvider {

    private static GithubRepository sGithubRepository;

    private RepositoryProvider() {
    }

    @NonNull
    public static GithubRepository provideGithubRepository() {
        if (sGithubRepository == null) {
            sGithubRepository = new DefaultGithubRepository();
        }
        return sGithubRepository;
    }


    /*@MainThread
    public static void init() {
        sGithubRepository = new DefaultGithubRepository();
    }*/
}
