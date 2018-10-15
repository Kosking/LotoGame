package my.game.loto.choiceAction.repository;

import java.util.List;

import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject;
import rx.Observable;

public interface ChoicePreference {
    Observable<String[]> getPreferences();
    void setPreferences(String[] preferences);
    void setIdStartingObject(String idPlayer);
    StartingObject getStartingObject(String[] stringsPreferences);
    void setTestToken(String myToken);
    String getTestToken();
    void setPlayerName(String playerName);
    void setListPlayObjects(List<PlayObject> listPlayObjects);
    Observable<StartObject> getStartObject();
}
