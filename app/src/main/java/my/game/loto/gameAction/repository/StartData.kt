package my.game.loto.gameAction.repository

data class StartData(val playerDiamonds: String,
                     val playerName: String,
                     val fullCards: Map<Int, String>) {
}