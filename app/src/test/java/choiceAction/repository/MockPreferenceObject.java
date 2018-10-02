package choiceAction.repository;

import android.support.annotation.NonNull;

import java.util.List;

import my.game.loto.choiceAction.repository.ChoicePreference;
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
import rx.Observable;

public class MockPreferenceObject implements ChoicePreference {

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
    public StartingObject getStartingObject() {
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
    public PrimaryData getPrimaryData() {
        return null;
    }

    @Override
    public void setPlayerName(String playerName) {
    }

    @Override
    public void setListPlayObjects(List<PlayObject> listPlayObjects) {

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
