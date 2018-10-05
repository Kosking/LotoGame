package my.game.loto.choiceAction.repository;

import java.util.List;

import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
import rx.Observable;

public interface ChoicePreference {
    Observable<String[]> getPreferences();
    void setPreferences(String[] preferences);
    void setIdStartingObject(String idPlayer);
    StartingObject getStartingObject();
    void setTestToken(String myToken);
    String getPlayerName();
    String getTestToken();
    PrimaryData getPrimaryData();
    void setPlayerName(String playerName);
    void setListPlayObjects(List<PlayObject> listPlayObjects);
}
