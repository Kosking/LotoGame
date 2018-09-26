package my.game.loto.gameAction.screens

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import my.game.loto.R
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.choiceAction.screens.FrontFragment
import my.game.loto.gameAction.presenter.GamePresenter
import my.game.loto.gameAction.retrofit.settingsObjects.GamingObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import my.game.loto.gameAction.screens.helpingObjects.FullDataCards
import my.game.loto.gameAction.screens.helpingObjects.RestoredDataCards
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData
import ru.arturvasilov.rxloader.LoaderLifecycleHandler
import java.io.*

class GameActivity : FragmentActivity(), ResultFragment.NextFragment, GameView {

    private var CLEAN_DATA_CARDS_KEY = "myPlayerCasks"
    private var FULL_DATA_CARDS_KEY = "myPlayerCasks"
    private var RESULT__KEY = "myPlayerCasks"
    private var startingObject: List<PlayObject>? = null
    private var fullGameObject: FullGameObject? = null
    private lateinit var idCards: IntArray
    private lateinit var resultObject: ResultObject

    private lateinit var gamePresenter: GamePresenter
    private lateinit var gameFragment: GameFragment

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

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

        if (startingObject == null) {
            try {
                ObjectInputStream(FileInputStream("FullGameObject.out"))
                        .use { input -> fullGameObject = input.readObject() as? FullGameObject }
            } catch (e: ClassCastException) {
                //TODO with log4j
                e.printStackTrace()
            }

            idCards = fullGameObject!!.idsCards
        } else {
            idCards = startingObject!![0].idsCards
        }
        gamePresenter.getCards(idCards)
        setNamesPlayers()
        setImagesPlayers()
        setDiamondsPlayers()

        gamePresenter.startGame(listOf("null"))
    }

    override fun setFullCards(fullCards: String) {
        val bundle = Bundle()
        if (startingObject == null) {
            val restoredDataCards = RestoredDataCards(
                    fullGameObject!!.crossedOutCells,
                    fullGameObject!!.greenCells,
                    fullGameObject!!.visibleCask)
            val fullDataCards = FullDataCards(fullCards, restoredDataCards)
            bundle.putSerializable(FULL_DATA_CARDS_KEY, fullDataCards)
        } else {
            bundle.putString(CLEAN_DATA_CARDS_KEY, fullCards)
        }
        gameFragment = GameFragment()
        gameFragment.arguments = bundle

        val fragTrans = fragmentManager.beginTransaction()
        fragTrans.add(R.id.container_for_game_frag, gameFragment)
        fragTrans.commit()
    }

    override fun setGameData(gamingObject: GamingObject) {
        gameFragment.setData(gamingObject)
    }

    override fun nextResultFragment(result: ResultObject) {
        resultObject = result
        val bundle = Bundle()
        bundle.putSerializable(RESULT__KEY, resultObject)

        val resultFragment = ResultFragment()
        resultFragment.arguments = bundle

        val fragTrans = fragmentManager.beginTransaction()
        fragTrans.add(R.id.container_for_game_frag, resultFragment)
        fragTrans.commit()
    }

    override fun toChoiceFragment() {
        setPrimaryData()
        val intent = Intent(this, FrontFragment::class.java)
        intent.putExtra("toChoiceFragment", "true")
        startActivity(intent)
    }

    override fun toFrontFragment() {
        setPrimaryData()
        val intent = Intent(this, FrontFragment::class.java)
        startActivity(intent)
    }

    private fun setPrimaryData(){
        val primaryData = PrimaryData(resultObject.playerMoney, resultObject.playerDiamonds)
        try {
            ObjectOutputStream(FileOutputStream("PrimaryData.out")).use { output -> output.writeObject(primaryData) }
        } catch (e: IOException) {
            //TODO with log4j
            e.printStackTrace()
        }
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
