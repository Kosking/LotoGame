package my.game.loto.gameAction.repository

import android.preference.PreferenceManager
import my.game.loto.AppDelegate



    val CARDS = "playersCards"
    private const val PLAYER_ID = "thisPlayerId"

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext())



    fun getStartingData(cards: IntArray) {

        /*val getStringsPreferences = arrayOfNulls<String>(6)
    getStringsPreferences[0] = sharedPreferences.getString(SPEED, "slow")
    getStringsPreferences[1] = sharedPreferences.getString(MODE_CARDS, "short")
    getStringsPreferences[2] = sharedPreferences.getString(MODE_ROOM, "open")
    getStringsPreferences[3] = sharedPreferences.getString(QUANTITY_PLAYERS, "two")
    getStringsPreferences[4] = sharedPreferences.getString(RATE, "100")
    getStringsPreferences[5] = getPlayerName()
    return Observable.just<Array<String>>(getStringsPreferences)*/

    }

    val playerId: String
        get() {
            return sharedPreferences.getString(PLAYER_ID, "")
        }
