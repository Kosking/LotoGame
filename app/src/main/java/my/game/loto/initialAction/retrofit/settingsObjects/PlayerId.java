package my.game.loto.initialAction.retrofit.settingsObjects;

public class PlayerId {

    private String id;

    public PlayerId(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        PlayerId playerId = (PlayerId) obj;
        if (playerId.getId().equals(getId())) {
            return true;
        }
        return false;
    }
}
