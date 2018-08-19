package my.game.loto.initialAction.retrofit.settingsObjects;

import java.io.Serializable;
import java.util.List;

public class FullGameObject implements Serializable {

    private String[] idsCards;
    private String[] crossedOutCells;
    private String[] greenCells;
    private String[] visibleCask;
    private List<OtherPlayers> otherPlayersList;
    private String playerDiamonds;

    public String[] getIdsCards() {
        return idsCards;
    }

    public void setIdsCards(String[] idsCards) {
        this.idsCards = idsCards;
    }


    public String[] getCrossedOutCells() {
        return crossedOutCells;
    }

    public void setCrossedOutCells(String[] myCrossedOutCells) {
        this.crossedOutCells = myCrossedOutCells;
    }


    public String[] getGreenCells() {
        return greenCells;
    }

    public void setGreenCells(String[] greenCells) {
        this.greenCells = greenCells;
    }


    public String[] getVisibleCask() {
        return visibleCask;
    }

    public void setVisibleCask(String[] visibleCask) {
        this.visibleCask = visibleCask;
    }


    public List<OtherPlayers> getOtherPlayersList() {
        return otherPlayersList;
    }

    public void setOtherPlayersList(List<OtherPlayers> otherPlayersList) {
        this.otherPlayersList = otherPlayersList;
    }


    public String getPlayerDiamonds() {
        return playerDiamonds;
    }

    public void setPlayerDiamonds(String playerDiamonds) {
        this.playerDiamonds = playerDiamonds;
    }
}
