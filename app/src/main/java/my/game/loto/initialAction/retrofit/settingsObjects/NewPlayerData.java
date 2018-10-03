package my.game.loto.initialAction.retrofit.settingsObjects;

import java.util.List;

public class NewPlayerData {

    private String id;
    private List<String> allFullCards;
    private String playerMoney;
    private String playerDiamonds;

    public List<String> getAllFullCards() { return allFullCards; }

    public void setAllFullCards(List<String> allFullCards) { this.allFullCards = allFullCards; }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        NewPlayerData playerData = (NewPlayerData) obj;
        if (playerData.getId().equals(getId())) {
            if (playerData.getAllFullCards().equals(getAllFullCards())) {
                if (playerData.getPlayerMoney().equals(getPlayerMoney())) {
                    if (playerData.getPlayerDiamonds().equals(getPlayerDiamonds())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
