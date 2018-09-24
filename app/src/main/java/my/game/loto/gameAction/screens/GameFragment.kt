package my.game.loto.gameAction.screens

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.game.loto.R
import my.game.loto.gameAction.retrofit.settingsObjects.GamingObject

class GameFragment : Fragment() {


    private var PLAYER_CARDS_KEY = "myPlayerCasks"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        val view = inflater.inflate(R.layout.game_fragment, null)

        setCards()
        return view
    }

    private fun setCards() {
        val bundle = this.arguments
        val fullCards = bundle.getString(PLAYER_CARDS_KEY)
    }


    fun setData(gamingObject: GamingObject){

    }
}