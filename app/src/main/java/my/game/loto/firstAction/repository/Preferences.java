package my.game.loto.firstAction.repository;

import my.game.loto.firstAction.retrofit.SettingsObjects.StartingObject;
import rx.Observable;

public interface Preferences {
    Observable<String[]> getPreferences();
    void setPreferences(String[] preferences);
    void setStringsPreferences(String[] myStringsPreferences);
    void setIdStartingObject(String idPlayer);
    StartingObject getStartingObject();
    String getToken();

}
