package my.game.loto.firstAction.retrofit;

import android.support.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


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
                .client(OkHttpProvider.getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
