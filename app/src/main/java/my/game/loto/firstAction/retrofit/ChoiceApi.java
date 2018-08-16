package my.game.loto.firstAction.retrofit;

import android.support.annotation.NonNull;

import my.game.loto.AppDelegate;

public final class ChoiceApi {

    private static ChoiceService service;

    private ChoiceApi() {
    }

    @NonNull
    public static ChoiceService getRetrofitService() {
        service = AppDelegate.buildRetrofit().create(ChoiceService.class);
        return service;
    }


}
