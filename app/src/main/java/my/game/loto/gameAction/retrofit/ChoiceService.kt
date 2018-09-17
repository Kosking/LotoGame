package my.game.loto.gameAction.retrofit

import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject
import my.game.loto.gameAction.retrofit.settingsObjects.PlayObject
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


interface ChoiceService {

    @POST("getPlayObject")
    fun getGame(@Body startingObject: StartingObject): Observable<List<PlayObject>>

}
