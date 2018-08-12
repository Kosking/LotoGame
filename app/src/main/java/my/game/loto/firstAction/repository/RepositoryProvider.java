package my.game.loto.firstAction.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;


public final class RepositoryProvider {

    private static ConnectRepository connectingRepository;
    private static Preferences preferenceObject;


    private RepositoryProvider() {
    }

    @MainThread
    public static void init() {
        connectingRepository = new ConnectingRepository();
        preferenceObject = new PreferenceObject();
    }

    @NonNull
    public static ConnectRepository provideConnectingRepository() {
        if (connectingRepository == null) {
            connectingRepository = new ConnectingRepository();
        }
        return connectingRepository;
    }

    @NonNull
    public static Preferences providePreferenceObject() {
        if (preferenceObject == null) {
            preferenceObject = new PreferenceObject();
        }
        return preferenceObject;
    }

    //TODO Del, its for tests
    public static void setConnectingRepository(@NonNull ConnectRepository connectRepository) {
        connectingRepository = connectRepository;
    }
    //TODO Del, its for tests
    public static void setPreferenceObject(@NonNull Preferences preferences) {
        preferenceObject = preferences;
    }

}
