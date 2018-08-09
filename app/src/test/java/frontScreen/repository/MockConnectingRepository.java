package frontScreen.repository;

import java.util.List;

import my.game.loto.firstAction.repository.ConnectRepository;
import my.game.loto.firstAction.retrofit.SettingsObjects.PlayObject;
import rx.Observable;

public class MockConnectingRepository implements ConnectRepository {

    private String[] stringsPreferences;

    @Override
    //TODO TEST
    public Observable<List<PlayObject>> startGame() {
        List<PlayObject> listObject = null;
        //PlayObject playObject = new PlayObject();
        //listObject.add(playObject);
        return Observable.just(listObject);
    }



}
