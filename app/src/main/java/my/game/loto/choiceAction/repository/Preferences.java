package my.game.loto.choiceAction.repository;

import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject;
import rx.Observable;

public interface Preferences {
    Observable<String[]> getPreferences();
    void setPreferences(String[] preferences);
    void setIdStartingObject(String idPlayer);
    StartingObject getStartingObject();
    String getTestToken();
    void setTestToken(String myToken);
    String getPlayerName();
    void setPlayerName(String playerName);
}
