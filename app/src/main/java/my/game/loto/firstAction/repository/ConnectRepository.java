package my.game.loto.firstAction.repository;

import java.util.List;

import my.game.loto.firstAction.retrofit.SettingsObjects.PlayObject;
import rx.Observable;

public interface ConnectRepository {

    Observable<List<PlayObject>> startGame();
}
