package my.game.loto.gameAction.screens

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import my.game.loto.R

class GameFragment : Fragment() {


    private var PLAYER_CARDS_KEY = "myPlayerCasks"



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle): View? {
        val view = inflater.inflate(R.layout.choice_fragment, null)

        setCards()
        return view
    }

    private fun setCards() {
        val fullCards: String
        val bundle = this.arguments
        if (bundle != null) {
            fullCards = bundle.getString(PLAYER_CARDS_KEY)
            if (fullCards != null) {
                setFullCards(fullCards)
            }
        }
    }

    private fun setFullCards(fullCards: String) {

    }
}