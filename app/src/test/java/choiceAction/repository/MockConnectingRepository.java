package choiceAction.repository;

import java.util.List;

import my.game.loto.choiceAction.repository.ConnectRepository;
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import rx.Observable;

public class MockConnectingRepository implements ConnectRepository {

    @Override
    public Observable<List<PlayObject>> startGame(String[] stringsPreferences) {
        return Observable.just(null);
    }
}
