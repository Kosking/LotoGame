package choiceAction.repository;

import android.support.annotation.NonNull;

import java.util.List;

import my.game.loto.choiceAction.repository.ChoicePreference;
import my.game.loto.choiceAction.repository.StartObject;
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject;
import rx.Observable;

public class MockChoiceObject implements ChoicePreference {

    @Override
    public void setPreferences(String preferences[]) {
    }

    @Override
    @NonNull
    public Observable<String[]> getPreferences() {
        return Observable.just(null);
    }

    @Override
    public void setIdStartingObject(String idPlayer) {
    }

    @Override
    public StartingObject getStartingObject(String[] stringsPreferences) {
        return null;
    }

    @Override
    public void setTestToken(String myToken) {

    }

    @Override
    public String getTestToken() {
        return null;
    }

    @Override
    public void setPlayerName(String playerName) {
    }

    @Override
    public void setListPlayObjects(List<PlayObject> listPlayObjects) {

    }

    @Override
    public Observable<StartObject> getStartObject() {
        return Observable.just(null);
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
