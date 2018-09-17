package choiceAction.repository;

import android.support.annotation.NonNull;

import my.game.loto.choiceAction.repository.ChoicePreference;
import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject;
import rx.Observable;

public class MockPreferenceObject implements ChoicePreference {
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
    public void setIdStartingObject(String idPlayer) {

    }

    @Override
    public StartingObject getStartingObject() {
        return null;
    }

    @Override
    public String getTestToken() {
        return null;
    }

    @Override
    public void setTestToken(String myToken) {

    }

    @Override
    public String getPlayerName() {
        return null;
    }

    @Override
    public void setPlayerName(String playerName) {
    }

    private String[] getDefaultPreferences() {
        String[] stringsReturned = new String[6];
        stringsReturned[0] = "slow";
        stringsReturned[1] = "short";
        stringsReturned[2] = "open";
        stringsReturned[3] = "two";
        stringsReturned[4] = "100";
        stringsReturned[5] = "root";
        return stringsReturned;
    }
}
