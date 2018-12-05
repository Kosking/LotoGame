package my.game.loto.gameAction.retrofit

import my.game.loto.AppDelegate
import my.game.loto.choiceAction.retrofit.ChoiceApi

object GameApi {
    @Volatile
    private var service: GameService? = null

    val gameService: GameService?
        get() {
            if (service == null) {
                synchronized(ChoiceApi::class.java) {
                    if (service == null) {
                        service = AppDelegate.buildRetrofit().create(GameService::class.java)
                    }
                }
            }
            return service
        }
}
