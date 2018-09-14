package my.game.loto.firstAction.retrofit;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import rx.android.BuildConfig;

public final class OkHttpProvider {

    private OkHttpProvider() {
    }

    @NonNull
    public static OkHttpClient provideClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getLoggingInterceptor())
                .addInterceptor(new RetryRequestInterceptor())
                .build();
    }

    @NonNull
    private static HttpLoggingInterceptor getLoggingInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG){
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY );
        }
        return loggingInterceptor;
    }
}
