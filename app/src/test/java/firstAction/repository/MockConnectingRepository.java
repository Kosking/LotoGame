package firstAction.repository;

import java.util.List;

import my.game.loto.firstAction.repository.ConnectRepository;
import my.game.loto.firstAction.retrofit.settingsObjects.PlayObject;
import rx.Observable;

public class MockConnectingRepository implements ConnectRepository {


    @Override
    public Observable<List<PlayObject>> startGame() {
        return Observable.just(null);
    }



}
