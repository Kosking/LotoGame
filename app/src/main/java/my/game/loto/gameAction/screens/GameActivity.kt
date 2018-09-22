package my.game.loto.gameAction.screens

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import my.game.loto.R
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.gameAction.presenter.GamePresenter
import my.game.loto.gameAction.retrofit.settingsObjects.GamingObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import ru.arturvasilov.rxloader.LoaderLifecycleHandler
import java.io.FileInputStream
import java.io.ObjectInputStream

class GameActivity : FragmentActivity(), GameView {

    private lateinit var gamePresenter: GamePresenter
    private var PLAYER_CARDS_KEY = "myPlayerCasks"
    private var startingObject: List<PlayObject>? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lifecycleHandler = LoaderLifecycleHandler.create(this, supportLoaderManager)
        gamePresenter = GamePresenter(this, lifecycleHandler)

        start()
    }

    private fun start() {
        try {
            ObjectInputStream(FileInputStream("StartObjects.out"))
                    .use { input -> startingObject = input.readObject() as? List<PlayObject> }
        } catch (e: ClassCastException) {
            //TODO with log4j
            e.printStackTrace()
        }
        val idCards = startingObject!![0].idsCards
        gamePresenter.getCards(idCards)
        setNamesPlayers()
        setImagesPlayers()
        setDiamondsPlayers()

        val greenCasks = Array<String>(1){"null"}
        gamePresenter.startGame(greenCasks)
    }

    override fun setFullCards(fullCards: String) {
        val bundle = Bundle()
        bundle.putString(PLAYER_CARDS_KEY, fullCards)
        val gameFragment = GameFragment()
        gameFragment.arguments = bundle

        val fragTrans = fragmentManager.beginTransaction()
        fragTrans.add(R.id.container_for_frag, gameFragment)
        fragTrans.commit()
    }

    override fun setGameData(gamingObject: GamingObject) {

    }

    override fun nextResultFragment(result: ResultObject) {

    }

    private fun setImagesPlayers() {
    }

    private fun setNamesPlayers() {
    }

    private fun setDiamondsPlayers() {
    }

    override fun showError() {
    }
}
