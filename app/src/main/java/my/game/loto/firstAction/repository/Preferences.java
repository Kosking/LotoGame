package my.game.loto.firstAction.repository;

import my.game.loto.firstAction.retrofit.settingsObjects.StartingObject;
import rx.Observable;

public interface Preferences {
    Observable<String[]> getPreferences();
    void setPreferences(String[] preferences);
    void setIdStartingObject(String idPlayer);
    StartingObject getStartingObject();
    String getToken();
    void setToken(String myToken);
}
