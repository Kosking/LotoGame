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
    public StartingObject getStartingObject(String[] stringsPreferences) {
        return null;
    }

    @Override
    public void setListPlayObjects(List<PlayObject> listPlayObjects) {
    }

    @Override
    public Observable<StartObject> getStartObject() {
        return Observable.just(null);
    }
}
