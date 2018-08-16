package my.game.loto.initialAction;

import android.support.annotation.NonNull;

import my.game.loto.AppDelegate;

public class InitialApi {

    private static InitialService service;

    private InitialApi() {
    }

    @NonNull
    public static InitialService getRetrofitService() {
        service = AppDelegate.buildRetrofit().create(InitialService.class);
        return service;
    }


}
