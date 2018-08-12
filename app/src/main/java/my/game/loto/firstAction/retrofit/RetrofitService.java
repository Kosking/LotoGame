package my.game.loto.firstAction.retrofit;

import java.util.List;

import my.game.loto.firstAction.retrofit.settingsObjects.PlayObject;
import my.game.loto.firstAction.retrofit.settingsObjects.StartingObject;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;


public interface RetrofitService {

    @POST("getPlayObject")
    Observable<List<PlayObject>> getGame(@Body StartingObject startingObject );

    /*@GET("/user/repos")
    Observable<List<Repository>> repositories();

    @GET("/repos/{user}/{repo}/commits")
    Observable<List<CommitResponse>> commits(@Path("user") String user, @Path("repo") String repo);*/

}
