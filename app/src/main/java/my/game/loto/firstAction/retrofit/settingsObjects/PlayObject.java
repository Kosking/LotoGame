package my.game.loto.firstAction.retrofit.settingsObjects;

import java.io.Serializable;

public class PlayObject implements Serializable {

    private int[] idsCards;
    private String namePlayer;
    private String imagePlayer;

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
