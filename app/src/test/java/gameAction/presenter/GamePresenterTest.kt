package gameAction.presenter

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import forTests.MockLifecycleHandler
import forTests.RxJavaResetRule
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.gameAction.presenter.GamePresenter
import my.game.loto.gameAction.repository.GameObject
import my.game.loto.gameAction.repository.GameProvider
import my.game.loto.gameAction.repository.GamingRepository
import my.game.loto.gameAction.retrofit.settingsObjects.GamingObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import my.game.loto.gameAction.screens.GameActivity
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.robolectric.RobolectricTestRunner
import rx.Observable
import java.util.TreeSet
import kotlin.collections.ArrayList
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
class GamePresenterTest {

    private val gameActivity: GameActivity = mock()
    private val lifecycleHandler: MockLifecycleHandler = MockLifecycleHandler()
    private val gameObject: GameObject = mock()
    private val gamingRepository: GamingRepository = mock()

    private val gamePresenter: GamePresenter = GamePresenter(gameActivity, lifecycleHandler)

    @Rule
    @JvmField
    val pluginsReset = RxJavaResetRule()

    @Before
    fun setUp() {
        GameProvider.gameObject = gameObject
        GameProvider.gamingRepository = gamingRepository
    }

    @Test
    fun createdPresenterTest(){
        assertNotNull(gamePresenter)
    }

    @Test
    fun startWithNonEmptyObjectTest(){
        val playObject = PlayObject()
        playObject.idsCards = IntArray(3){it}
        val listPlayers = arrayListOf(playObject)
        val fullCards = ArrayList<TreeSet<String>>()
        whenever(gameObject.listPlayToken).thenReturn("true")
        whenever(gameObject.getListPlayers()).thenReturn(Observable.just(listPlayers))
        whenever(gameObject.getFullCards(playObject.idsCards))
                .thenReturn(Observable.just(fullCards))
        gamePresenter.start()

        Mockito.verify(gameActivity).setStartingData(fullCards, listPlayers)
        Mockito.verify(gameActivity, times(0)).showError()
    }

    @Test
    fun startWithEmptyObjectTest(){
        val fullGameObject = FullGameObject()
        fullGameObject.idsCards = IntArray(3){it}
        val fullCards = ArrayList<TreeSet<String>>()
        whenever(gameObject.listPlayToken).thenReturn("false")
        whenever(gameObject.getFullGameObject()).thenReturn(Observable.just(fullGameObject))
        whenever(gameObject.getFullCards(fullGameObject.idsCards))
                .thenReturn(Observable.just(fullCards))
        gamePresenter.start()

        Mockito.verify(gameActivity).setFullStartingData(fullCards, fullGameObject)
        Mockito.verify(gameActivity, times(0)).showError()
    }

    @Test
    fun getDataTest(){
        val gamingObject = GamingObject("","","" )
        val resultObject = ResultObject("","","","")
        val stringGreenCasks = "nonNull"
        gamePresenter.greenCasks = listOf(stringGreenCasks)
        whenever(gameObject.gameSpeedInSeconds).thenReturn(1000)
        whenever(gamingRepository.getGameData(listOf(stringGreenCasks)))
                .thenReturn(Observable.just(gamingObject))
        whenever(gamingRepository.getResultData()).thenReturn(Observable.just(resultObject))
        gamePresenter.getData()

        Mockito.verify(gameActivity).setReceivedData(gamingObject)
        Mockito.verify(gameActivity).nextResultFragment(resultObject)
        Mockito.verify(gameActivity, times(0)).showError()
    }

    @Test
    fun setNextFragmentDataTest(){
        val resultObject = ResultObject("","","","")
        gamePresenter.setNextFragmentData(resultObject)

        Mockito.verify(gameObject).setPrimaryData(resultObject)
        Mockito.verify(gameActivity, times(0)).showError()
    }
}