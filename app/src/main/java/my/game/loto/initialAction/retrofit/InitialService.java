package my.game.loto.initialAction.retrofit;

import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerSettings;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerId;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerToken;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
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

    @POST("createNewPlayer")
    Observable<NewPlayerData> createNewPlayer(@Body NewPlayerSettings newPlayerSettings);

    /*@Multipart
    @POST("createNewPlayer")
    Observable<NewPlayerData> createNewPlayer(@Part("description") String newPlayerName, @Part("image") RequestBody imagePlayer);*/
}
