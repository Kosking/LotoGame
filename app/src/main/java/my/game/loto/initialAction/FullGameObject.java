package my.game.loto.initialAction;

import java.io.Serializable;
import java.util.List;

class FullGameObject implements Serializable {

    private String[] idsCards;
    private String[] labeledCell;
    private String[] visibleCask;
    private List<OtherPlayers> otherPlayersList;
    private String playerDiamonds;

    public String[] getIdsCards() {
        return idsCards;
    }

    public void setIdsCards(String[] idsCards) {
        this.idsCards = idsCards;
    }

    public String[] getLabeledCell() {
        return labeledCell;
    }

    public void setLabeledCell(String[] labeledCell) {
        this.labeledCell = labeledCell;
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
