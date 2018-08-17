package my.game.loto.initialAction.retrofit.settingsObjects;

import java.io.Serializable;

public class PrimaryData implements Serializable {

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
}
