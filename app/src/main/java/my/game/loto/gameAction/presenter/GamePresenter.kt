package my.game.loto.gameAction.presenter

import my.game.loto.R
import my.game.loto.gameAction.repository.getFullCards
import my.game.loto.gameAction.repository.getGameData
import my.game.loto.gameAction.repository.getResultData
import my.game.loto.gameAction.screens.GameView
import ru.arturvasilov.rxloader.LifecycleHandler
import rx.Observable
import java.util.concurrent.TimeUnit

class GamePresenter(private val gameActivity: GameView,
                    private val lifecycleHandler: LifecycleHandler) {

    var greenCasks: List<String> = listOf("null")

    fun getCards(cards: IntArray) {
        val fullCards = getFullCards(cards)
        gameActivity.setFullCards(fullCards)
    }

    fun getData() {
        Observable
                .defer{ -> Observable.just(greenCasks)}
                .flatMap{greenCasks -> getGameData(greenCasks)}
                .repeatWhen{objectObservable -> objectObservable.delay(1, TimeUnit.SECONDS).take(90)}
                .takeUntil{data-> data.finishGame == "true"}
                .compose(lifecycleHandler.load(R.id.getPreferences))
                .subscribe({gameData -> gameActivity.setReceivedData(gameData)},
                        { throwable -> gameActivity.showError()})
        getResultGame()
    }

    private fun getResultGame() {
        getResultData()
                .compose(lifecycleHandler.load(R.id.getPreferences))
                .subscribe({ result -> gameActivity.nextResultFragment(result)},
                        { throwable -> gameActivity.showError()})

    }

}