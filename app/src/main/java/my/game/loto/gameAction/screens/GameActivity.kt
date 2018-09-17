package my.game.loto.gameAction.screens

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.gameAction.presenter.GamePresenter
import my.game.loto.gameAction.repository.initRepository
import ru.arturvasilov.rxloader.LoaderLifecycleHandler
import java.io.FileInputStream
import java.io.ObjectInputStream

class GameActivity : FragmentActivity() {

    private lateinit var gamePresenter: GamePresenter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lifecycleHandler = LoaderLifecycleHandler.create(this, supportLoaderManager)
        gamePresenter = GamePresenter(this, lifecycleHandler)
        initRepository()

        setSerializableObject()
        gamePresenter.startGame()
    }

    private fun setSerializableObject() {
        try {
            ObjectInputStream(FileInputStream("StartObjects.out"))
                    .use { input -> val startingObject = input.readObject() as? List<PlayObject> }
        } catch (e: ClassCastException) {
            //TODO with log4j
            e.printStackTrace()
        }

        setCards()
        setNamesPlayers()
        setImagesPlayers()
    }

    private fun setImagesPlayers() {

    }

    private fun setNamesPlayers() {

    }

    private fun setCards() {

    }
}
