package my.game.loto.firstAction.retrofit;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;

public final class OkHttpProvider {

    private OkHttpProvider() {
    }

    @NonNull
    public static OkHttpClient provideClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(MockingInterceptor.create())
                .build();
    }
}
