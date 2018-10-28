package my.game.loto.gameAction.retrofit

import my.game.loto.gameAction.retrofit.settingsObjects.CasksObject
import my.game.loto.gameAction.retrofit.settingsObjects.GamingObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


interface GameService {

    @POST("getGameData")
    fun getGameData(@Body startingObject: CasksObject): Observable<GamingObject>

    @POST("getResultData")
    fun getResultData(@Body myPlayerId: String): Observable<ResultObject>
}
