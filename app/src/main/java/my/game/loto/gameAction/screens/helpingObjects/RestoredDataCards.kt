package my.game.loto.gameAction.screens.helpingObjects

import java.util.*

data class RestoredDataCards(val crossedOutCells: Array<String>,
                             val greenCells: Array<String>,
                             val visibleCask: Array<String>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RestoredDataCards

        if (!Arrays.equals(crossedOutCells, other.crossedOutCells)) return false
        if (!Arrays.equals(greenCells, other.greenCells)) return false
        if (!Arrays.equals(visibleCask, other.visibleCask)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(crossedOutCells)
        result = 31 * result + Arrays.hashCode(greenCells)
        result = 31 * result + Arrays.hashCode(visibleCask)
        return result
    }
}