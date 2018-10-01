package my.game.loto.gameAction.repository

import android.preference.PreferenceManager
import my.game.loto.AppDelegate
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData
import java.io.*

val CARDS = "playersCards"
private const val PLAYER_ID = "thisPlayerId"
private const val SPEED = "slow"
private lateinit var speed: String

private var listPlayers: List<PlayObject>? = null
private var fullGameObject: FullGameObject? = null
private lateinit var idCards: IntArray

val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext())!!


fun getListPlayers(): List<PlayObject>? {
    try {
        ObjectInputStream(FileInputStream("StartObjects.out"))
                .use { input -> listPlayers = input.readObject() as? List<PlayObject> }
    } catch (e: ClassCastException) {
        //TODO with log4j
        e.printStackTrace()
    }
    return listPlayers
}

fun getFullGameObject(): FullGameObject? {
    try {
        ObjectInputStream(FileInputStream("FullGameObject.out"))
                .use { input -> fullGameObject = input.readObject() as? FullGameObject }
    } catch (e: ClassCastException) {
        //TODO with log4j
        e.printStackTrace()
    }
    return fullGameObject
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
    val primaryData = PrimaryData(resultObject.playerMoney, resultObject.playerDiamonds)
    try {
        ObjectOutputStream(FileOutputStream("PrimaryData.out")).use { output -> output.writeObject(primaryData) }
    } catch (e: IOException) {
        //TODO with log4j
        e.printStackTrace()
    }
}
