package my.game.loto.gameAction.screens

import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.gameAction.retrofit.settingsObjects.GamingObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject
import java.util.*

interface GameView {

    fun showError()
    fun setReceivedData(gamingObject: GamingObject)
    fun nextResultFragment(result: ResultObject)
    fun setStartingData(fullCards: ArrayList<TreeSet<String>>, listPlayObjects: List<PlayObject>?)
    fun setFullStartingData(fullCards: ArrayList<TreeSet<String>>, fullGameObject: FullGameObject?)
}