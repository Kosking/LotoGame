package my.game.loto.choiceAction.repository;

import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;

public class StartObject {

    private String playerName;
    private PrimaryData primaryData;

    public StartObject(String playerName, PrimaryData primaryData){
        this.playerName = playerName;
        this.primaryData = primaryData;
    }

    public String getPlayerName() { return playerName; }

    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public PrimaryData getPrimaryData() { return primaryData; }

    public void setPrimaryData(PrimaryData primaryData) { this.primaryData = primaryData; }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        StartObject startObject = (StartObject) obj;
        if (startObject.getPlayerName().equals(getPlayerName())) {
            if (startObject.getPrimaryData().equals(getPrimaryData())){
                return true;
            }
        }
        return false;
    }
}
