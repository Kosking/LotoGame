package gameAction.repository

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.test.runner.AndroidJUnit4
import forTest.RxSchedulersTestRule
import my.game.loto.AppDelegate
import my.game.loto.choiceAction.retrofit.TestToken
import my.game.loto.gameAction.repository.GamingRepository
import my.game.loto.gameAction.retrofit.settingsObjects.GamingObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.adapter.rxjava.HttpException
import rx.observers.TestSubscriber

@RunWith(AndroidJUnit4::class)
class GamingRepositoryTest {

    private val MY_TOKEN = "error"
    private val PLAY_TOKEN = "root"
    private val PLAYER_ID = "thisPlayerId"
    private val LATE_CASKS = IntArray(4)
    private val IDS_MISSED_CASKS = IntArray(4)
    private val FINISH_GAME = "false"
    private val WINNERS = Array(2){""}
    private val PLAYER_DIAMONDS = "30"
    private val PLAYER_MONEY = "300"
    private val REMAINING_CASKS = IntArray(3){it}

    private lateinit var sharedPreferences: SharedPreferences

    @Rule
    @JvmField
    var mRule = RxSchedulersTestRule()

    @Before
    fun setUp() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext())
        testPlayerId = PLAY_TOKEN
    }

    @Test
    fun getGameDataTest() {
        val myGamingObject = GamingObject(LATE_CASKS, IDS_MISSED_CASKS, FINISH_GAME)
        val gamingObject = GamingRepository.getGameData(listOf()).toBlocking().first()

        assertTrue(myGamingObject == gamingObject)
    }

    @Test
    fun errorGetGameDataTest() {
        TestToken.testToken = MY_TOKEN

        val testSubscriber = TestSubscriber<GamingObject>()
        GamingRepository.getGameData(listOf()).subscribe(testSubscriber)

        testSubscriber.assertError(HttpException::class.java)
    }

    @Test
    fun getResultDataTest() {
        val myResultObject = ResultObject(WINNERS, PLAYER_MONEY, PLAYER_DIAMONDS, REMAINING_CASKS)

        val resultObject = GamingRepository.getResultData().toBlocking().first()
        assertTrue(myResultObject == resultObject)
    }

    @Test
    fun errorGetResultDataTest() {
        TestToken.testToken = MY_TOKEN

        val testSubscriber = TestSubscriber<ResultObject>()
        GamingRepository.getResultData().subscribe(testSubscriber)

        testSubscriber.assertError(HttpException::class.java)
    }

    @After
    fun setTrueTestToken() { TestToken.testToken = "" }

    private var testPlayerId: String = ""
        set(value) {
            val editor = sharedPreferences.edit()
            editor.putString(PLAYER_ID, value)
            editor.apply()
        }
}