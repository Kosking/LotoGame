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
import ru.arturvasilov.rxloader.LoaderLifecycleHandler

class GameActivity : FragmentActivity(), ResultFragment.NextFragment, GameFragment.GamingFragment, GameView {

    private var CLEAN_DATA_CARDS_KEY = "myPlayerCasks"
    private var FULL_DATA_CARDS_KEY = "myPlayerCasks"
    private var RESULT__KEY = "myPlayerCasks"

    private var listPlayObjects: List<PlayObject>? = null
    private var fullGameObject: FullGameObject? = null

    private lateinit var resultObject: ResultObject

    private lateinit var gamePresenter: GamePresenter
    private lateinit var gameFragment: GameFragment

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_activity)

        val lifecycleHandler = LoaderLifecycleHandler.create(this, supportLoaderManager)
        gamePresenter = GamePresenter(this, lifecycleHandler)

        gamePresenter.start()
    }


    override fun setStartingData(fullCards: String, listPlayObjects: List<PlayObject>?) {
        this.listPlayObjects = listPlayObjects
        val bundle = Bundle()
        bundle.putString(CLEAN_DATA_CARDS_KEY, fullCards)
        nextGameFragment(bundle)
    }

    override fun setFullStartingData(fullCards: String, fullGameObject: FullGameObject?) {
        this.fullGameObject = fullGameObject
        val bundle = Bundle()
        val restoredDataCards = RestoredDataCards(
                fullGameObject!!.crossedOutCells,
                fullGameObject.greenCells,
                fullGameObject.visibleCask)
        val fullDataCards = FullDataCards(fullCards, restoredDataCards)
        bundle.putSerializable(FULL_DATA_CARDS_KEY, fullDataCards)
        nextGameFragment(bundle)
    }

    private fun nextGameFragment(bundle: Bundle) {
        gameFragment = GameFragment()
        gameFragment.arguments = bundle

        val fragTrans = fragmentManager.beginTransaction()
        fragTrans.add(R.id.container_for_game_frag, gameFragment)
        fragTrans.commit()

        setNamesPlayers()
        setImagesPlayers()
        setDiamondsPlayers()
    }

    override fun setOutwardData(greenCasks: List<String>) {
        gamePresenter.greenCasks = greenCasks
        if (greenCasks[0] == "null") {
            gamePresenter.getData()
        }
    }

    override fun setReceivedData(gamingObject: GamingObject) {
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
        gamePresenter.setNextFragmentData(resultObject)
        val intent = Intent(this, FrontFragment::class.java)
        intent.putExtra("toChoiceFragment", "true")
        startActivity(intent)
    }

    override fun toFrontFragment() {
        gamePresenter.setNextFragmentData(resultObject)
        val intent = Intent(this, FrontFragment::class.java)
        startActivity(intent)
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
