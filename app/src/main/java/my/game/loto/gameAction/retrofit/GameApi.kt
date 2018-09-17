package my.game.loto.gameAction.retrofit

import my.game.loto.AppDelegate

object GameApi {
    private lateinit var service: ChoiceService

    val retrofitService: ChoiceService
        get() {
            service = AppDelegate.buildRetrofit().create(ChoiceService::class.java)
            return service
        }
}
