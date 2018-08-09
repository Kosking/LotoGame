package my.game.loto.firstAction.retrofit;

import my.game.loto.firstAction.retrofit.SettingsObjects.PlayObject;
import my.game.loto.firstAction.retrofit.SettingsObjects.StartingObject;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import rx.Observable;


public interface RetrofitService {

    @GET("/matchMaker")
    Observable<List<PlayObject>> getGame(@Body StartingObject params);

    /*@GET("/user/repos")
    Observable<List<Repository>> repositories();

    @GET("/repos/{user}/{repo}/commits")
    Observable<List<CommitResponse>> commits(@Path("user") String user, @Path("repo") String repo);*/

}
