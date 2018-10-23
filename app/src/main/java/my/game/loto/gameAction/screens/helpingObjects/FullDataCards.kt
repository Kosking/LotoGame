package my.game.loto.gameAction.screens.helpingObjects

import java.io.Serializable
import java.util.*

data class FullDataCards(val fullCards: ArrayList<TreeSet<String>>,
                         val restoredDataCards: RestoredDataCards) : Serializable