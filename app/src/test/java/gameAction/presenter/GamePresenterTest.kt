package gameAction.presenter

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import forTests.MockLifecycleHandler
import forTests.RxJavaResetRule
import my.game.loto.gameAction.presenter.GamePresenter
import my.game.loto.gameAction.repository.GameObject
import my.game.loto.gameAction.repository.GameProvider
import my.game.loto.gameAction.repository.GamingRepository
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import my.game.loto.gameAction.screens.GameActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.robolectric.RobolectricTestRunner
import rx.Observable
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
class GamePresenterTest {

    private val gameActivity: GameActivity = mock()
    private val lifecycleHandler: MockLifecycleHandler = MockLifecycleHandler()
    private val gameObject: GameObject = mock()
    private val gamingRepository: GamingRepository = mock()

    @Before
    fun setUp() {
        GameProvider.gameObject = gameObject
        GameProvider.gamingRepository = gamingRepository
    }
    private val gamePresenter: GamePresenter = GamePresenter(gameActivity, lifecycleHandler)

    @Rule
    @JvmField
    val pluginsReset = RxJavaResetRule()

    @Test
    fun createdPresenterTest(){
        assertNotNull(gamePresenter)
    }

    @Test
    fun startWithNonEmptyObjectTest(){
        whenever(gameObject.getListPlayers()).thenReturn(Observable.just(arrayListOf()))


    }

    @Test
    fun startWithEmptyObjectTest(){

    }

    @Test
    fun getDataTest(){

    }

    @Test
    fun setNextFragmentDataTest(){
        val resultObject = ResultObject("","","","")
        gamePresenter.setNextFragmentData(resultObject)
        Mockito.verify(gameObject).setPrimaryData(resultObject)
        Mockito.verify(gameActivity, times(0)).showError()
    }
}