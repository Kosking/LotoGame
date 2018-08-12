package my.game.loto.firstAction.retrofit;

import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class MockingInterceptor implements Interceptor {

    private final RequestsHandler mHandlers;

    private MockingInterceptor() {
        mHandlers = new RequestsHandler();
    }

    @NonNull
    public static Interceptor create() {
        return new MockingInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String path = request.url().encodedPath();
        if (mHandlers.shouldIntercept(path)) {
            Response response = mHandlers.proceed(request, path);
            int stubDelay = 500;
            SystemClock.sleep(stubDelay);
            return response;
        }
        return chain.proceed(request);
    }
}

