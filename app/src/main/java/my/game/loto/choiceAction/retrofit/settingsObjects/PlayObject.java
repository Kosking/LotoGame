package my.game.loto.choiceAction.retrofit.settingsObjects;

import java.io.Serializable;

public class PlayObject implements Serializable {

    private int[] idsCards;
    private String namePlayer;
    private String imagePlayer;
    private String playerDiamonds;


    public String getPlayerDiamonds() { return playerDiamonds; }

    public void setPlayerDiamonds(String playerDiamonds) { this.playerDiamonds = playerDiamonds; }

    public int[] getIdsCards() {
        return idsCards;
    }

    public void setIdsCards(int[] idsCards) {
        this.idsCards = idsCards;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public String getImagePlayer() {
        return imagePlayer;
    }

    public void setImagePlayer(String imagePlayer) {
        this.imagePlayer = imagePlayer;
    }
}
