package my.game.loto.choiceAction.repository;

import java.util.List;

import my.game.loto.choiceAction.retrofit.ChoiceApi;
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;


public class ConnectingRepository implements ConnectRepository {

    @Override
    public Observable<List<PlayObject>> startGame(){
        return ChoiceApi
                .getRetrofitService()
                .getGame(RepositoryProvider.providePreferenceObject().getStartingObject())
                .compose(RxUtils.async());
    }
}