package my.game.loto.initialAction.retrofit.settingsObjects;

public class NewPlayerSettings {

    private String imagePlayer;
    private String playerName;

    public NewPlayerSettings(String imagePlayer, String playerName){
        this.imagePlayer = imagePlayer;
        this.playerName = playerName;
    }

    public String getImagePlayer() {
        return imagePlayer;
    }

    public void setImagePlayer(String imagePlayer) {
        this.imagePlayer = imagePlayer;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        NewPlayerSettings playerSettings = (NewPlayerSettings) obj;
        if (playerSettings.getPlayerName().equals(getPlayerName())) {
            if (playerSettings.getImagePlayer().equals(getImagePlayer())){
                return true;
            }
        }
        return false;
    }
}
