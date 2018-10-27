package my.game.loto.gameAction.retrofit.settingsObjects

import java.io.Serializable
import java.util.*

data class ResultObject(val winners: Array<String>,
                        val playerMoney: String,
                        val playerDiamonds: String,
                        val remainingCasks: IntArray) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResultObject

        if (!Arrays.equals(winners, other.winners)) return false
        if (playerMoney != other.playerMoney) return false
        if (playerDiamonds != other.playerDiamonds) return false
        if (!Arrays.equals(remainingCasks, other.remainingCasks)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(winners)
        result = 31 * result + playerMoney.hashCode()
        result = 31 * result + playerDiamonds.hashCode()
        result = 31 * result + Arrays.hashCode(remainingCasks)
        return result
    }
}