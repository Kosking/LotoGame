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
}
