package my.game.loto.initialAction.retrofit.settingsObjects;

public class OtherPlayers {

    private String namePlayer;
    private String imagePlayer;

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
        OtherPlayers otherPlayers = (OtherPlayers) obj;
        if (otherPlayers.getNamePlayer().equals(getNamePlayer())) {
            if (otherPlayers.getImagePlayer().equals(getImagePlayer())){
                return true;
            }
        }
        return false;
    }
}
