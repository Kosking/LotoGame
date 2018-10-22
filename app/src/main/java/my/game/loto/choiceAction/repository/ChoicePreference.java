package my.game.loto.choiceAction.repository;

import java.util.List;

import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject;
import rx.Observable;

public interface ChoicePreference {
    Observable<String[]> getPreferences();
    void setPreferences(String[] preferences);
    StartingObject getStartingObject(String[] stringsPreferences);
    void setListPlayObjects(List<PlayObject> listPlayObjects);
    Observable<StartObject> getStartObject();
}
