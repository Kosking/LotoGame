package my.game.loto.gameAction.screens

import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.gameAction.retrofit.settingsObjects.GamingObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject

interface GameView {

    fun showError()
    fun setReceivedData(gamingObject: GamingObject)
    fun nextResultFragment(result: ResultObject)
    fun setStartingData(fullCards: String, listPlayObjects: List<PlayObject>?)
    fun setFullStartingData(fullCards: String, fullGameObject: FullGameObject?)
}