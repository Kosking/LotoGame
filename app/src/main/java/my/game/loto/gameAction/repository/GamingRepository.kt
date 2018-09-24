package my.game.loto.gameAction.repository

import my.game.loto.gameAction.retrofit.GameApi
import my.game.loto.gameAction.retrofit.settingsObjects.CasksObject
import my.game.loto.gameAction.retrofit.settingsObjects.GamingObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import ru.arturvasilov.rxloader.RxUtils
import rx.Observable


private var myPlayerId: String? = null

fun getGameData(greenCasks: List<String>): Observable<GamingObject> {
    return GameApi
            .gameService
            .getGameData(getCasksObject(greenCasks))
            .compose(RxUtils.async())
}

private fun getCasksObject(greenCasks: List<String>): CasksObject {
    if (myPlayerId == null){
        myPlayerId = playerId
    }
    return CasksObject(myPlayerId!!, greenCasks)
}

fun getResultData(): Observable<ResultObject>{
    return GameApi
            .gameService
            .getResultData(myPlayerId!!)
            .compose(RxUtils.async())
}

