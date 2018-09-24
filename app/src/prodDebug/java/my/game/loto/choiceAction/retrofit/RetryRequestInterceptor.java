package my.game.loto.choiceAction.retrofit;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RetryRequestInterceptor implements Interceptor {

    private static final int RETRY_DELAY = 1000;
    private static final int MAX_TRY_COUNT_ERROR = 7;

    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        int tryCountError = 0;
        while (response.code() != 200 && tryCountError <= MAX_TRY_COUNT_ERROR) {
            //TODO DEL Log.d
            Log.d("Kostya", "Request is not successful - " + tryCountError);

            tryCountError++;
            try {
                Thread.sleep(RETRY_DELAY * tryCountError);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            response = chain.proceed(request);
        }
        return response;
    }
}
