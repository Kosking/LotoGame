package my.game.loto.initialAction.retrofit.settingsObjects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
@Entity(tableName = "full_game_object")
public class FullGameObject implements Serializable {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "ids_cards")
    private int[] idsCards;
    @ColumnInfo(name = "crossed_out_cells")
    private String[] crossedOutCells;
    @ColumnInfo(name = "green_cells")
    private String[] greenCells;
    @ColumnInfo(name = "visible_cask")
    private String[] visibleCask;
    @ColumnInfo(name = "other_players_list")
    private List<OtherPlayers> otherPlayersList;
    @ColumnInfo(name = "player_diamonds")
    private String playerDiamonds;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int[] getIdsCards() {
        return idsCards;
    }

    public void setIdsCards(int[] idsCards) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        FullGameObject gameObject = (FullGameObject) obj;
        if (Arrays.equals(gameObject.getIdsCards(), getIdsCards())) {
            if (Arrays.equals(gameObject.getCrossedOutCells(), getCrossedOutCells())) {
                if (Arrays.equals(gameObject.getGreenCells(), getGreenCells())) {
                    if (Arrays.equals(gameObject.getVisibleCask(), getVisibleCask())) {
                        if (gameObject.getOtherPlayersList().equals(getOtherPlayersList())) {
                            if (gameObject.getPlayerDiamonds().equals(getPlayerDiamonds())) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
