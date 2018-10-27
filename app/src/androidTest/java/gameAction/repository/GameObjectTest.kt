package gameAction.repository

import android.arch.persistence.room.Room
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import forTest.RxSchedulersTestRule
import my.game.loto.AppDelegate
import my.game.loto.choiceAction.repository.room.ChoiceDao
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject
import my.game.loto.gameAction.repository.GameObject
import my.game.loto.gameAction.retrofit.settingsObjects.ResultObject
import my.game.loto.initialAction.repository.room.InitialDao
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject
import my.game.loto.initialAction.retrofit.settingsObjects.OtherPlayers
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData
import my.game.loto.room.AppDatabase
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class GameObjectTest {

    private val LIST_PLAY_TOKEN = "listPlayObject"
    private val PLAY_TOKEN = "root"
    private val NUMBER_OF_PLAYERS = "numberOfPlayers"
    private val NUMBER_PLAYER = 2
    private val MY_PLAYER_NAME = "rootRoot"
    private val MY_NON_PLAYER_NAME = "nonRoot"
    private val PLAYER_SETTINGS = arrayOf("myPlayer", "testRoot")
    private val PLAYER_NAME = "thisPlayerId"
    private val PLAYER_DIAMONDS = "30"
    private val PLAYER_MONEY = "300"
    private val CARDS = "playersCards"
    private val SPEED = "mySpeed"
    private val SPEED_NORMAL = "normal"
    private val SPEED_NORMAL_IN_SECONDS = 2.toLong()
    private val PLAYER_ID = "thisPlayerId"


    private lateinit var myListPlayers: ArrayList<PlayObject>
    private lateinit var myFullGameObject: FullGameObject
    private lateinit var idsCards: IntArray
    private lateinit var myFullCards: ArrayList<TreeSet<String>>

    private lateinit var db: AppDatabase
    private lateinit var choiceDao: ChoiceDao
    private lateinit var initialDao: InitialDao
    private lateinit var sharedPreferences: SharedPreferences

    @Rule
    @JvmField
    val mRule = RxSchedulersTestRule()

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase::class.java)
                .build()
        AppDelegate.setDatabase(db)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext())
    }

    @Test
    fun listPlayTokenTest(){
        myListPlayToken = PLAY_TOKEN
        assertTrue(GameObject.listPlayToken == PLAY_TOKEN)
        assertTrue(myListPlayToken == "")
    }

    @Test
    fun getListPlayersTest() {
        setMyListPlayers()
        myNumberPlayers = NUMBER_PLAYER
        choiceDao = db.choiceDao()
        choiceDao.setPlayObjects(myListPlayers)
        GameObject
                .getListPlayers()
                .subscribe({listPlayers -> assertTrue(myListPlayers == listPlayers)},
                        { throwable ->  })
    }

    @Test
    fun getFullGameObjectTest(){
        setTestFullGameObject()
        initialDao = db.initialDao()
        initialDao.setFullGameObject(myFullGameObject)
        GameObject
                .getFullGameObject()
                .subscribe({fullGameObject -> assertTrue(myFullGameObject == fullGameObject)},
                        { throwable ->  })
    }

    @Test
    fun getFullCardsTest(){
        setTestFullCards()
        GameObject
                .getFullCards(idsCards)
                .subscribe({fullCards -> assertTrue(myFullCards == fullCards)},
                        { throwable ->  })
    }

    @Test
    fun gameSpeedInSecondsTest(){
        gameSpeedTestInSeconds = SPEED_NORMAL
        assertTrue(GameObject.gameSpeedInSeconds == SPEED_NORMAL_IN_SECONDS)
    }

    @Test
    fun playerIdTest(){
        testPlayerId = PLAY_TOKEN
        assertTrue(GameObject.playerId == PLAY_TOKEN)
    }

    @Test
    fun setPrimaryDataTest(){
        val resultObject = ResultObject(arrayOf(), PLAYER_MONEY, PLAYER_DIAMONDS,IntArray(3))
        val primaryData = PrimaryData(0, PLAYER_MONEY, PLAYER_DIAMONDS )
        GameObject.setPrimaryData(resultObject)
        choiceDao = db.choiceDao()
        val primaryDatar = choiceDao.getPrimaryData()
        assertTrue(primaryData == primaryDatar)
    }

    @After
    fun closeDb() {
        db.close()
    }


    private var myListPlayToken: String
        get(){
            return sharedPreferences.getString(LIST_PLAY_TOKEN, "")
        }
        set(token){
            val editor = sharedPreferences.edit()
            editor.putString(LIST_PLAY_TOKEN, token)
            editor.apply()
        }

    private var myNumberPlayers: Int
    get(){
        return sharedPreferences.getInt(NUMBER_OF_PLAYERS, 1)
    }
    set(value) {
        val editor = sharedPreferences.edit()
        editor.putInt(NUMBER_OF_PLAYERS, value)
        editor.apply()
    }

    private fun setMyListPlayers() {
        myListPlayers = ArrayList()
        val playObject = PlayObject()
        playObject.id = 0
        playObject.namePlayer = MY_NON_PLAYER_NAME
        playObject.imagePlayer = "myImage"
        playObject.idsCards = IntArray(2)
        playObject.playerDiamonds = "1000"
        playObject.start = "true"
        val playObject2 = PlayObject()
        playObject2.id = 1
        playObject2.namePlayer = MY_PLAYER_NAME
        playObject2.imagePlayer = "myImage"
        playObject2.idsCards = IntArray(2)
        playObject2.playerDiamonds = "1000"
        playObject2.start = "true"
        myListPlayers.add(playObject)
        myListPlayers.add(playObject2)
    }

    private fun setTestFullGameObject() {
        myFullGameObject = FullGameObject()
        myFullGameObject.idsCards = intArrayOf(1)
        myFullGameObject.crossedOutCells = PLAYER_SETTINGS
        myFullGameObject.visibleCask = PLAYER_SETTINGS
        val allFullCards = ArrayList<OtherPlayers>()
        val otherPlayers = OtherPlayers()
        otherPlayers.namePlayer = PLAYER_NAME
        otherPlayers.imagePlayer = "myImage"
        allFullCards.add(0, otherPlayers)
        myFullGameObject.otherPlayersList = allFullCards
        myFullGameObject.greenCells = PLAYER_SETTINGS
        myFullGameObject.playerDiamonds = PLAYER_DIAMONDS
    }

    private fun setTestFullCards(){
        val cards = TreeSet<String>()
        cards.add("21")
        cards.add("12")
        val cards2 = TreeSet<String>()
        cards2.add("75")
        cards2.add("89")
        val cards3 = TreeSet<String>()
        cards3.add("7")
        cards3.add("8")
        myFullCards = ArrayList(3)
        myFullCards.add(cards)
        myFullCards.add(cards2)
        myFullCards.add(cards3)
        val editor = sharedPreferences.edit()
        for (i in myFullCards.indices) {
            editor.putStringSet(CARDS + i, myFullCards[i])
        }
        editor.apply()
        idsCards = IntArray(3)
        for (i in 0..2) {
            idsCards[i] = i
        }
    }

    private var gameSpeedTestInSeconds: String = ""
        set(value) {
            val editor = sharedPreferences.edit()
            editor.putString(SPEED, value)
            editor.apply()
        }

    private var testPlayerId: String = ""
        set(value) {
            val editor = sharedPreferences.edit()
            editor.putString(PLAYER_ID, value)
            editor.apply()
        }
}