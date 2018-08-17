package my.game.loto.initialAction.retrofit;

import android.support.annotation.NonNull;

import my.game.loto.AppDelegate;

public final class InitialApi {

    //TODO volatile or not
    private static volatile InitialService service;

    private InitialApi() {
    }

    @NonNull
    public static InitialService getRetrofitService() {
        if (service == null) {
            synchronized (InitialApi.class) {
                if (service == null) {
                    service = AppDelegate.buildRetrofit().create(InitialService.class);
                }
            }
        }
        return service;
    }


}
