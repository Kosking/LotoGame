package my.game.loto.choiceAction.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import my.game.loto.AppDelegate;
import my.game.loto.choiceAction.repository.RepositoryProvider;
import my.game.loto.initialAction.repository.InitialProvider;
import okhttp3.Request;
import okhttp3.Response;


public class RequestsHandler {

    private final Map<String, String> mResponsesMap = new HashMap<>();

    public RequestsHandler() {
        mResponsesMap.put("getPlayObject", "PlayObject.json");
        mResponsesMap.put("getPlayerToken", "PlayerToken.json");
        mResponsesMap.put("getPlayData", "FullGameObject.json");
        mResponsesMap.put("getPrimaryData", "PrimaryData.json");
        mResponsesMap.put("createNewPlayer", "NewPlayerData.json");
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
        String tokenFirstAction = RepositoryProvider.providePreferenceObject().getTestToken();
        String tokenInitialAction = InitialProvider.provideInitialObject().getTestToken();
        if ("error".equals(tokenFirstAction)) {
            return OkHttpResponse.error(request, 400, "Error for path " + path);
        } else if("error".equals(tokenInitialAction)){
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
        try (InputStream stream = context.getAssets().open(assetPath)){

            return OkHttpResponse.success(request, stream);
        } catch (IOException e) {
            return OkHttpResponse.error(request, 500, e.getMessage());
        }
    }
}
