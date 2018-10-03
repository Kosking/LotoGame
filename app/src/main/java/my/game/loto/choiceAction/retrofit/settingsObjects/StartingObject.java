package my.game.loto.choiceAction.retrofit.settingsObjects;

import java.util.Arrays;

public class StartingObject {

    private String id;
    private String[] stringsSettings;

    public StartingObject(String id, String[] stringsSettings){
        this.id = id;
        this.stringsSettings = stringsSettings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getStringsSettings() {
        return stringsSettings;
    }

    public void setStringsSettings(String[] stringsSettings) {
        this.stringsSettings = stringsSettings;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        StartingObject startingObject = (StartingObject) obj;
        if (startingObject.getId().equals(getId())) {
            if (Arrays.equals(startingObject.getStringsSettings(), getStringsSettings())){
                return true;
            }
        }
        return false;
    }
}
