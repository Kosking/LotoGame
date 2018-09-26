package my.game.loto.gameAction.retrofit.settingsObjects

import java.io.Serializable

data class ResultObject(val winners:String,
                        val playerMoney: String,
                        val playerDiamonds: String,
                        val remainingCasks: String) : Serializable