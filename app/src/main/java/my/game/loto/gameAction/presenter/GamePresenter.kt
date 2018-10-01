package my.game.loto.gameAction.presenter

import my.game.loto.R
import my.game.loto.gameAction.repository.*
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import my.game.loto.gameAction.screens.GameView
import ru.arturvasilov.rxloader.LifecycleHandler
import rx.Observable
import java.util.concurrent.TimeUnit

class GamePresenter(private val gameActivity: GameView,
                    private val lifecycleHandler: LifecycleHandler) {

    var greenCasks: List<String> = listOf("null")

    fun start() {
        val listPlayers = getListPlayers()
        if (listPlayers!!.isEmpty()) {
            val fullGameObject = getFullGameObject()
            val fullCards = getFullCards(fullGameObject!!.idsCards)
            gameActivity.setFullStartingData(fullCards, fullGameObject)
        } else {
            val fullCards = getFullCards(listPlayers[0].idsCards)
            gameActivity.setStartingData(fullCards, listPlayers)
        }
    }

    fun getData() {
        val delayRequests = gameSpeedInSeconds
        Observable
                .defer { -> Observable.just(greenCasks) }
                .flatMap { greenCasks -> getGameData(greenCasks) }
                .repeatWhen { objectObservable -> objectObservable.delay(delayRequests, TimeUnit.SECONDS).take(90) }
                .takeUntil { data -> data.finishGame == "true" }
                .compose(lifecycleHandler.load(R.id.getPreferences))
                .subscribe({ gameData -> gameActivity.setReceivedData(gameData) },
                        { throwable -> gameActivity.showError() })
        getResultGame()
    }

    private fun getResultGame() {
        getResultData()
                .compose(lifecycleHandler.load(R.id.getPreferences))
                .subscribe({ result -> gameActivity.nextResultFragment(result) },
                        { throwable -> gameActivity.showError() })

    }

    fun setNextFragmentData(result: ResultObject) {
        setPrimaryData(result)
    }
}