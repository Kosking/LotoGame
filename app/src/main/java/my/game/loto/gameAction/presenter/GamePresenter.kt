package my.game.loto.gameAction.presenter

import my.game.loto.R
import my.game.loto.gameAction.repository.getGameData
import my.game.loto.gameAction.repository.getResultData
import my.game.loto.gameAction.screens.GameView
import ru.arturvasilov.rxloader.LifecycleHandler
import java.util.concurrent.TimeUnit

class GamePresenter(val gameActivity: GameView, val lifecycleHandler: LifecycleHandler) {

    fun getStartData(cards: IntArray) {

    }

    fun startGame(greenCasks: Array<String>) {
        getGameData(greenCasks)
                .repeatWhen{objectObservable -> objectObservable.delay(1, TimeUnit.SECONDS).take(90)}
                .takeUntil{data-> data.finishGame == ""}
                .compose(lifecycleHandler.load(R.id.getPreferences))
                .subscribe({gameData -> gameActivity.setGameData(gameData)},
                        { throwable -> gameActivity.showError()},
                        { -> getResultGame()})
    }

    private fun getResultGame() {
        getResultData()
                .compose(lifecycleHandler.load(R.id.getPreferences))
                .subscribe({ result -> gameActivity.nextResultFragment(result)},
                        { throwable -> gameActivity.showError()})

    }

}