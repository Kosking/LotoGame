package my.game.loto.choiceAction.repository;

import java.util.List;

import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import rx.Observable;

public interface ConnectRepository {

    Observable<List<PlayObject>> startGame();
}
