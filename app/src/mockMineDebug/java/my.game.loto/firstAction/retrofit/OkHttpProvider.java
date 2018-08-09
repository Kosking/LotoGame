package my.game.loto.firstAction.retrofit;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;

public final class OkHttpProvider {

    private OkHttpProvider() {
    }

    @NonNull
    public static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                //.addInterceptor(ApiKeyInterceptor.create())
                .build();
    }
}
