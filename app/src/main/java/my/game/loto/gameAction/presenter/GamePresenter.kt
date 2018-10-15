package my.game.loto.gameAction.presenter

import my.game.loto.R
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.gameAction.repository.*
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import my.game.loto.gameAction.screens.GameView
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject
import ru.arturvasilov.rxloader.LifecycleHandler
import ru.arturvasilov.rxloader.RxUtils
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class GamePresenter(private val gameActivity: GameView,
                    private val lifecycleHandler: LifecycleHandler) {

    @Volatile var greenCasks: List<String> = listOf("null")

    fun start() {
        getListPlayers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycleHandler.load(R.id.playObjectRetrofit))
                .subscribe(this::setGameObject ,
                        {throwable -> gameActivity.showError()})
    }

    private fun setGameObject(listPlayers: List<PlayObject>) {
        if (listPlayers!!.isEmpty()) {
            getFullGameObject()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(lifecycleHandler.load(R.id.playObjectRetrofit))
                    .subscribe(this::fullGame,
                            {throwable -> gameActivity.showError()})
        } else {
            getFullCards(listPlayers[0].idsCards)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(lifecycleHandler.load(R.id.playObjectRetrofit))
                    .subscribe({fullCards -> gameActivity.setStartingData(fullCards, listPlayers)},
                            {throwable -> gameActivity.showError()})
        }
    }

    private fun fullGame(fullGameObject: FullGameObject){
        getFullCards(fullGameObject!!.idsCards)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(lifecycleHandler.load(R.id.playObjectRetrofit))
                .subscribe({fullCards -> gameActivity.setFullStartingData(fullCards, fullGameObject)},
                        {throwable -> gameActivity.showError()})
    }

    fun getData() {
        val delayRequests = gameSpeedInSeconds
        Observable
                .defer { Observable.just(greenCasks) }
                .flatMap { greenCasks -> getGameData(greenCasks) }
                .repeatWhen { objectObservable -> objectObservable.delay(delayRequests, TimeUnit.SECONDS).take(90) }
                .takeUntil { data -> data.finishGame == "true" }
                .compose(RxUtils.async())
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

    fun setNextFragmentData(myResult: ResultObject) {
        Observable.just(myResult)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .compose(lifecycleHandler.load(R.id.setResultObject))
                .subscribe({ result -> setPrimaryData(result) },
                        { throwable -> gameActivity.showError() })
    }
}