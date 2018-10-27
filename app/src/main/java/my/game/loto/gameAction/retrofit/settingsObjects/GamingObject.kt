package my.game.loto.gameAction.retrofit.settingsObjects

import java.util.*

data class GamingObject(val lateCasks: IntArray,
                        val idsMissedCasks: IntArray,
                        val finishGame: String ) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GamingObject

        if (!Arrays.equals(lateCasks, other.lateCasks)) return false
        if (!Arrays.equals(idsMissedCasks, other.idsMissedCasks)) return false
        if (finishGame != other.finishGame) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(lateCasks)
        result = 31 * result + Arrays.hashCode(idsMissedCasks)
        result = 31 * result + finishGame.hashCode()
        return result
    }
}
