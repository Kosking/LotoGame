package my.game.loto.choiceAction.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;


public final class RepositoryProvider {

    private static ConnectRepository connectingRepository;
    private static ChoicePreference choiceObject;


    private RepositoryProvider() {
    }

    @MainThread
    public static void init() {
        connectingRepository = new ConnectingRepository();
        choiceObject = new ChoiceObject();
    }

    @NonNull
    public static ConnectRepository provideConnectingRepository() {
        if (connectingRepository == null) {
            connectingRepository = new ConnectingRepository();
        }
        return connectingRepository;
    }

    @NonNull
    public static ChoicePreference provideChoiceObject() {
        if (choiceObject == null) {
            choiceObject = new ChoiceObject();
        }
        return choiceObject;
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static void setConnectingRepository(@NonNull ConnectRepository connectRepository) {
        connectingRepository = connectRepository;
    }
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static void setChoiceObject(@NonNull ChoicePreference choicePreference) {
        choiceObject = choicePreference;
    }

}
