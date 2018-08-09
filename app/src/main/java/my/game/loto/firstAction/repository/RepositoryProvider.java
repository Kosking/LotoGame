package my.game.loto.firstAction.repository;

import android.content.SharedPreferences;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;


public final class RepositoryProvider {

    private static ConnectRepository connectingRepository;
    private static Preferences preferenceObject;

    private static SharedPreferences sharedPreferences;

    private RepositoryProvider() {
    }

    @NonNull
    public static ConnectRepository provideConnectingRepository() {
        if (connectingRepository == null) {
            connectingRepository = new ConnectingRepository();
        }
        return connectingRepository;
    }
    //TODO Del, its for tests
    public static void setConnectingRepository(@NonNull ConnectRepository connectRepository) {
        connectingRepository = connectRepository;
    }

    @NonNull
    public static Preferences providePreferenceObject() {
        if (preferenceObject == null) {
            preferenceObject = new PreferenceObject(sharedPreferences);
        }
        return preferenceObject;
    }
    //TODO Del, its for tests
    public static void setPreferenceObject(@NonNull Preferences preferences) {
        preferenceObject = preferences;
    }

    @MainThread
    public static void init(SharedPreferences mySharedPreferences) {
        sharedPreferences = mySharedPreferences;
        connectingRepository = new ConnectingRepository();
        preferenceObject = new PreferenceObject(sharedPreferences);
        //preferenceObject = new PreferenceObject();
    }
}
