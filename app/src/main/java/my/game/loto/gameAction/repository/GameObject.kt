package my.game.loto.gameAction.repository

import android.preference.PreferenceManager
import my.game.loto.AppDelegate
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData

val CARDS = "playersCards"
private const val PLAYER_ID = "thisPlayerId"
private const val SPEED = "slow"
private lateinit var speed: String

private val sharedPreferences = PreferenceManager.
        getDefaultSharedPreferences(AppDelegate.getContext())!!
private val database = AppDelegate.getDatabase()
private val gameDao = database.gameDao()


fun getListPlayers(): List<PlayObject> {
    val listPlayers = gameDao.getListPlayObjects()
    gameDao.deleteListPlayObjects()
    return listPlayers
}

fun getFullGameObject(): FullGameObject {
    return gameDao.getFullGameObject()
}

fun getFullCards(idCards: IntArray) = buildString {
    for (number in idCards) {
        append(sharedPreferences.getString(CARDS + number, ""))
    }
}

val gameSpeedInSeconds: Long
    get() {
        speed = sharedPreferences.getString(SPEED, "slow")
        return when (speed) {
            "slow" -> 3
            "normal" -> 2
            else -> 1
        }
    }

val playerId: String
    get() {
        return sharedPreferences.getString(PLAYER_ID, "")
    }


fun setPrimaryData(resultObject: ResultObject) {
    val primaryData = PrimaryData(0, resultObject.playerMoney, resultObject.playerDiamonds)
    gameDao.setPrimaryData(primaryData)
}
