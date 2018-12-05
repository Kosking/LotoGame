package my.game.loto.choiceAction.retrofit;

import android.support.annotation.NonNull;

import my.game.loto.AppDelegate;

public final class ChoiceApi {

    private static volatile ChoiceService service;

    private ChoiceApi() {
    }

    @NonNull
    public static ChoiceService getRetrofitService() {
        if (service == null) {
            synchronized (ChoiceApi.class) {
                if (service == null) {
                    service = AppDelegate.buildRetrofit().create(ChoiceService.class);
                }
            }
        }
        return service;
    }
}
