package my.game.loto;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import my.game.loto.choiceAction.retrofit.OkHttpProvider;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppDelegate extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    @NonNull
    public static Context getContext() {
        return sContext;
    }

    @NonNull
    public static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/")
                .client(OkHttpProvider.provideClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


}
