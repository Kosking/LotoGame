package my.game.loto.firstAction.retrofit.settingsObjects;

import java.io.Serializable;

public class PlayObject implements Serializable {

    private int[] myIdsCards;
    private String namePlayer;
    private String imagePlayer;

    public int[] getIdsCards() {
        return myIdsCards;
    }

    public void setIdsCards(int[] myIdsCards) {
        this.myIdsCards = myIdsCards;
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
