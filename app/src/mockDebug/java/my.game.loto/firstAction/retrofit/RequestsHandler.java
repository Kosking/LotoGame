package my.game.loto.firstAction.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import my.game.loto.AppDelegate;
import my.game.loto.firstAction.repository.RepositoryProvider;
import okhttp3.Request;
import okhttp3.Response;


public class RequestsHandler {

    private final Map<String, String> mResponsesMap = new HashMap<>();

    public RequestsHandler() {
        mResponsesMap.put("getPlayObject", "PlayObject.json");
        //mResponsesMap.put("/user/repos", "repositories.json");
    }

    public boolean shouldIntercept(@NonNull String path) {
        Set<String> keys = mResponsesMap.keySet();
        for (String interceptUrl : keys) {
            if (path.contains(interceptUrl)) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public Response proceed(@NonNull Request request, @NonNull String path) {
        String token = RepositoryProvider.providePreferenceObject().getToken();
        if ("error".equals(token)) {
            return OkHttpResponse.error(request, 400, "Error for path " + path);
        }

        Set<String> keys = mResponsesMap.keySet();
        for (String interceptUrl : keys) {
            if (path.contains(interceptUrl)) {
                String mockResponsePath = mResponsesMap.get(interceptUrl);
                return createResponseFromAssets(request, mockResponsePath);
            }
        }

        return OkHttpResponse.error(request, 500, "Incorrectly intercepted request");
    }

    @NonNull
    private Response createResponseFromAssets(@NonNull Request request, @NonNull String assetPath) {
        Context context = AppDelegate.getContext();
        try {
            try (InputStream stream = context.getAssets().open(assetPath)) {
                return OkHttpResponse.success(request, stream);
            }
        } catch (IOException e) {
            return OkHttpResponse.error(request, 300, e.getMessage());
        }
    }
}
