package my.game.loto.firstAction.retrofit;

import android.support.annotation.NonNull;



import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public final class ApiFactory {

    private static RetrofitService service;

    private ApiFactory() {
    }

    @NonNull
    public static RetrofitService getRetrofitService() {
        service = buildRetrofit().create(RetrofitService.class);
        return service;
    }

    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/")
                .client(OkHttpProvider.provideClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

}
