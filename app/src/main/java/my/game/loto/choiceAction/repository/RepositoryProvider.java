package my.game.loto.choiceAction.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;


public final class RepositoryProvider {

    private static ConnectRepository connectingRepository;
    private static ChoicePreference preferenceObject;


    private RepositoryProvider() {
    }

    @MainThread
    public static void init() {
        connectingRepository = new ConnectingRepository();
        preferenceObject = new ChoiceObject();
    }

    @NonNull
    public static ConnectRepository provideConnectingRepository() {
        if (connectingRepository == null) {
            connectingRepository = new ConnectingRepository();
        }
        return connectingRepository;
    }

    @NonNull
    public static ChoicePreference providePreferenceObject() {
        if (preferenceObject == null) {
            preferenceObject = new ChoiceObject();
        }
        return preferenceObject;
    }

    //TODO Del, its for tests
    public static void setConnectingRepository(@NonNull ConnectRepository connectRepository) {
        connectingRepository = connectRepository;
    }
    //TODO Del, its for tests
    public static void setPreferenceObject(@NonNull ChoicePreference choicePreference) {
        preferenceObject = choicePreference;
    }

}
