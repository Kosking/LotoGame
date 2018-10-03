package my.game.loto.initialAction.retrofit.settingsObjects;

import java.io.Serializable;

public class PrimaryData implements Serializable {

    private String playerMoney;
    private String playerDiamonds;

    public PrimaryData(String playerMoney, String playerDiamonds){
        this.playerMoney = playerMoney;
        this.playerDiamonds = playerDiamonds;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        PrimaryData primaryData = (PrimaryData) obj;
        if (primaryData.getPlayerMoney().equals(getPlayerMoney())) {
            if (primaryData.getPlayerDiamonds().equals(getPlayerDiamonds())){
                return true;
            }
        }
        return false;
    }
}
