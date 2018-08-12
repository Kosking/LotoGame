package frontScreen.repository;

import android.support.annotation.NonNull;

import my.game.loto.firstAction.repository.Preferences;
import my.game.loto.firstAction.retrofit.settingsObjects.StartingObject;
import rx.Observable;

public class MockPreferenceObject implements Preferences {
    private String[] stringsPreferences;

    @Override
    public void setPreferences(String preferences[]) {
        stringsPreferences = preferences;
    }

    @Override
    @NonNull
    public Observable<String[]> getPreferences() {
        if (stringsPreferences == null){
            stringsPreferences = getDefaultPreferences();
        }
        return Observable.just(stringsPreferences);
    }

    @Override
    public void setStringsPreferences(String[] myStringsPreferences) {

    }

    @Override
    public void setIdStartingObject(String idPlayer) {

    }

    @Override
    public StartingObject getStartingObject() {
        return null;
    }

    @Override
    public String getToken() {
        return null;
    }

    private String[] getDefaultPreferences() {
        String[] stringsReturned = new String[5];
        stringsReturned[0] = "slow";
        stringsReturned[1] = "short";
        stringsReturned[2] = "open";
        stringsReturned[3] = "two";
        stringsReturned[4] = "100";
        return stringsReturned;
    }
}
