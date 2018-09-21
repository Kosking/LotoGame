package my.game.loto.gameAction.repository

import android.preference.PreferenceManager
import my.game.loto.AppDelegate

    val CARDS = "playersCards"
    private const val PLAYER_ID = "thisPlayerId"

    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext())


    fun getFullCards(cards: IntArray) = buildString {
        for (number in cards){
            append(sharedPreferences.getString(CARDS + number, ""))
        }
    }

    val playerId: String
        get() {
            return sharedPreferences.getString(PLAYER_ID, "")
        }
