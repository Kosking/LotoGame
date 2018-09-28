package my.game.loto.gameAction.screens

import my.game.loto.gameAction.retrofit.settingsObjects.GamingObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject

interface GameView {

    fun showError()
    fun setGameData(gamingObject: GamingObject)
    fun nextResultFragment(result: ResultObject)
    fun setFullCards(fullCards: String)
}