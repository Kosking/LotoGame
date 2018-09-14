package my.game.loto.firstAction.retrofit;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RetryRequestInterceptor implements Interceptor {

    private int tryCountError = 0;
    private static final int MAX_TRY_COUNT_ERROR = 5;
    private static final int RETRY_BACKOFF_DELAY = 1000;

    private int tryCountGetGame = 0;
    private static final int MAX_TRY_GET_GAME = 60;
    private static final int RETRY_GET_GAME_DELAY = 1000;

    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        while (response.code() != 200 && tryCountError <= MAX_TRY_COUNT_ERROR) {
            //TODO DEL Log.d
            Log.d("Kostya", "Request is not successful - " + tryCountError);

            tryCountError++;
            try {
                Thread.sleep(RETRY_BACKOFF_DELAY * tryCountError);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            response = chain.proceed(request);
        }

        while (response.toString().equals("") && tryCountGetGame <= MAX_TRY_GET_GAME) {
            //TODO DEL Log.d
            Log.d("Kostya", "Request is not successful - " + tryCountGetGame);

            tryCountGetGame++;
            try {
                Thread.sleep(RETRY_GET_GAME_DELAY);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            response = chain.proceed(request);
        }

        return response;
    }
}
