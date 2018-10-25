package my.game.loto.gameAction.repository

import android.preference.PreferenceManager
import my.game.loto.AppDelegate
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData
import rx.Observable
import rx.Observable.fromCallable
import java.util.*

object GameObject {

    private const val CARDS = "playersCards"
    private const val PLAYER_ID = "thisPlayerId"
    private const val NUMBER_OF_PLAYERS = "numberOfPlayers"
    private const val SPEED = "slow"
    private lateinit var speed: String

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext())!!
    private val database = AppDelegate.getDatabase()
    private val gameDao = database.gameDao()


    fun getListPlayers(): Observable<List<PlayObject>> {
        return fromCallable({ getPlayers() })
    }

    private fun getPlayers(): List<PlayObject> {
        val lastNumberOfPlayer = sharedPreferences.getInt(NUMBER_OF_PLAYERS, 1)
        val numberOfPlayers = IntArray(lastNumberOfPlayer) { it }
        return gameDao.getListPlayObjects(numberOfPlayers)
    }

    fun getFullGameObject(): Observable<FullGameObject> {
        return fromCallable({ gameDao.getFullGameObject() })
    }

    fun getFullCards(idCards: IntArray): Observable<ArrayList<TreeSet<String>>> {
        return fromCallable({ getCards(idCards) })
    }

    private fun getCards(idCards: IntArray): ArrayList<TreeSet<String>> {
        val listCards: ArrayList<Set<String>> = arrayListOf()
        for (number in idCards) {
            listCards[number] = sharedPreferences.getStringSet(CARDS + number, setOf())
        }
        val treeSets: ArrayList<TreeSet<String>> = arrayListOf()
        treeSets.add(TreeSet(listCards[0]))
        treeSets.add(TreeSet(listCards[1]))
        treeSets.add(TreeSet(listCards[2]))
        return treeSets
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
}
