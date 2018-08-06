package com.example.work.loto.FirstAction.Repository.Retrofit;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.BuildConfig;


public final class ApiFactory {

    private ApiFactory() {
    }

    @NonNull
    public static RetrofitService getRetrofitService() {
        RetrofitService service = buildRetrofit().create(RetrofitService.class);
        return service;
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())
                //.addInterceptor(ApiKeyInterceptor.create())
                .build();
    }

    @NonNull
    private static HttpLoggingInterceptor getLoggingInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if(BuildConfig.DEBUG){
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY );
        }
        return loggingInterceptor;
    }
}
