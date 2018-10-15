package my.game.loto.initialAction.retrofit.settingsObjects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "primary_data")
public class PrimaryData {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "player_money")
    private String playerMoney;
    @ColumnInfo(name = "player_diamonds")
    private String playerDiamonds;

    public PrimaryData(int id, String playerMoney, String playerDiamonds){
        this.playerMoney = playerMoney;
        this.playerDiamonds = playerDiamonds;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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
