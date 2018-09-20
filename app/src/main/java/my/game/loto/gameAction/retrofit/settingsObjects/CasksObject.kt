package my.game.loto.gameAction.retrofit.settingsObjects

import java.util.*

data class CasksObject(var playerId: String, var greenCasks: Array<String>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CasksObject

        if (!Arrays.equals(greenCasks, other.greenCasks)) return false

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(greenCasks)
    }
}