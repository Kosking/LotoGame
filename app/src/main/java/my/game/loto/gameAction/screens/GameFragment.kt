package my.game.loto.gameAction.screens

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.game.loto.R
import my.game.loto.gameAction.retrofit.settingsObjects.GamingObject
import my.game.loto.gameAction.screens.helpingObjects.FullDataCards

class GameFragment : Fragment() {


    private var CLEAN_DATA_CARDS_KEY = "myPlayerCasks"
    private var FULL_DATA_CARDS_KEY = "myPlayerCasks"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        val view = inflater.inflate(R.layout.game_fragment, null)

        checkCards()
        return view
    }

    private fun checkCards() {
        val bundle = this.arguments
        val fullCards = bundle.getString(CLEAN_DATA_CARDS_KEY)
        if (fullCards != null){
            setFullCards(fullCards)
        }else{
            val fullDataCards = bundle.getSerializable(FULL_DATA_CARDS_KEY) as? FullDataCards
            setFullDataCards(fullDataCards)
        }
    }

    private fun setFullCards(fullCards: String?) {

    }

    private fun setFullDataCards(fullDataCards: FullDataCards?) {

    }


    fun setData(gamingObject: GamingObject){

    }
}