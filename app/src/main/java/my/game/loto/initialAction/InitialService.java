package my.game.loto.initialAction;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface InitialService {

    @POST("getPlayerToken")
    Observable<PlayerToken> playerToken(@Body PlayerId playerId);

    @POST("getPlayData")
    Observable<FullGameObject> getPlayData(@Body PlayerId playerId);

    @POST("getPrimaryData")
    Observable<PrimaryData> getPrimaryData(@Body PlayerId playerId);
}
