package my.game.loto.gameAction.retrofit

import my.game.loto.AppDelegate

object GameApi {
    private lateinit var service: GameService

    val gameService: GameService
        get() {
            service = AppDelegate.buildRetrofit().create(GameService::class.java)
            return service
        }
}
