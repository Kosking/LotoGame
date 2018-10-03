package my.game.loto.choiceAction.retrofit.settingsObjects;

import java.io.Serializable;
import java.util.Arrays;

public class PlayObject implements Serializable {

    private int[] idsCards;
    private String start;
    private String namePlayer;
    private String imagePlayer;
    private String playerDiamonds;


    public String getStart() { return start; }

    public void setStart(String start) { this.start = start; }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        PlayObject playObject = (PlayObject) obj;
        if (Arrays.equals(playObject.getIdsCards(), getIdsCards())) {
            if (playObject.getStart().equals(getStart())){
                if (playObject.getNamePlayer().equals(getNamePlayer())) {
                    if (playObject.getImagePlayer().equals(getImagePlayer())){
                        if (playObject.getPlayerDiamonds().equals(getPlayerDiamonds())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
