package my.game.loto;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;


public class AppDelegate extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    @NonNull
    public static Context getContext() {
        return sContext;
    }


}
