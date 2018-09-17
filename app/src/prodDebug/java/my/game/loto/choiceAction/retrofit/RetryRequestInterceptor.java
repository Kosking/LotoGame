package my.game.loto.choiceAction.retrofit;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RetryRequestInterceptor implements Interceptor {

    private static final int RETRY_DELAY = 1000;

    private static final int MAX_TRY_COUNT_ERROR = 5;
    private static final int MAX_TRY_GET_GAME = 60;

    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        final Response[] response = {chain.proceed(request)};

        class RetryRequestClass {

            private int tryCountError;
            private int tryCountGetGame;

            private RetryRequestClass() {
                tryCountError = 0;
                tryCountGetGame = 0;
            }

            private void checkRetryRequest() throws InterruptedException, IOException {
                if (response[0].code() != 200) {
                    retryError();
                } else if (response[0].code() == 200) {
                    retryGetGame();
                }
            }

            private void retryError() throws InterruptedException, IOException {
                while (response[0].code() != 200 && tryCountError <= MAX_TRY_COUNT_ERROR) {
                    //TODO DEL Log.d
                    Log.d("Kostya", "Request is not successful - " + tryCountError);

                    tryCountError++;
                    Thread.sleep(RETRY_DELAY * tryCountError);


                    response[0] = chain.proceed(request);
                }
                if (response[0].code() == 200) {
                    retryGetGame();
                }
            }

            private void retryGetGame() throws InterruptedException, IOException {
                Headers headers = response[0].headers();
                String getGameHeader = headers.get("GetGame");
                if (getGameHeader != null) {
                    while (getGameHeader.equals("") && tryCountGetGame <= MAX_TRY_GET_GAME) {
                        //TODO DEL Log.d
                        Log.d("Kostya", "GetGame is null - " + tryCountGetGame);

                        tryCountGetGame++;
                        Thread.sleep(RETRY_DELAY);

                        response[0] = chain.proceed(request);
                        if (response[0].code() != 200) {
                            retryError();
                        }
                    }
                }
            }
        }

        RetryRequestClass retryClass = new RetryRequestClass();
        try {
            retryClass.checkRetryRequest();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return response[0];
    }
}
