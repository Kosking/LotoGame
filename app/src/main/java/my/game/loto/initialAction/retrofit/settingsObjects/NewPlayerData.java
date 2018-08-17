package my.game.loto.initialAction.retrofit.settingsObjects;

public class NewPlayerData {

    private String id;
    private String playerMoney;
    private String playerDiamonds;

    public String getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(String playerMoney) {
        this.playerMoney = playerMoney;
    }

    public String getPlayerDiamonds() {
        return playerDiamonds;
    }

    public void setPlayerDiamonds(String playerDiamonds) {
        this.playerDiamonds = playerDiamonds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
