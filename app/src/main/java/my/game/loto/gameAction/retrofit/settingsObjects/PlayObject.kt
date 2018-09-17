package my.game.loto.gameAction.retrofit.settingsObjects

import java.io.Serializable

data class PlayObject(val idsCasks: String,
                      val idsMissedCasks: String,
                      val finishGame: String ) : Serializable {
}
