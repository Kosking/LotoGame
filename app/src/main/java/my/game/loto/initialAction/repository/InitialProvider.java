package my.game.loto.initialAction.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

public final class InitialProvider {

    private static PrepareRepository preparatoryRepository;
    private static InitialPreference initialObject;


    private InitialProvider() {
    }

    @MainThread
    public static void init() {
        preparatoryRepository = new PreparatoryRepository();
        initialObject = new InitialObject();
    }

    @NonNull
    public static PrepareRepository providePreparatoryRepository() {
        if (preparatoryRepository == null) {
            preparatoryRepository = new PreparatoryRepository();
        }
        return preparatoryRepository;
    }

    @NonNull
    public static InitialPreference provideInitialObject() {
        if (initialObject == null) {
            initialObject = new InitialObject();
        }
        return initialObject;
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static void setPreparatoryRepository(@NonNull PrepareRepository prepareRepository) {
        preparatoryRepository = prepareRepository;
    }
    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static void setPreferenceObject(@NonNull InitialPreference initialPreference) {
        initialObject = initialPreference;
    }

}