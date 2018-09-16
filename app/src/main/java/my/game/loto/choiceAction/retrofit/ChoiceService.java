package my.game.loto.choiceAction.retrofit;

import java.util.List;

import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;


public interface ChoiceService {

    @POST("getPlayObject")
    Observable<List<PlayObject>> getGame(@Body StartingObject startingObject );

}
