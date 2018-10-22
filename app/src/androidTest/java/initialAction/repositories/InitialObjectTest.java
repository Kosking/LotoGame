package initialAction.repositories;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import my.game.loto.AppDelegate;
import my.game.loto.choiceAction.repository.room.ChoiceDao;
import my.game.loto.gameAction.repository.room.GameDao;
import my.game.loto.initialAction.repository.InitialProvider;
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.OtherPlayers;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerId;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
import my.game.loto.room.AppDatabase;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class InitialObjectTest {

    private NewPlayerData newPlayerData;
    private ArrayList<TreeSet<String>> allFullCards;
    private PrimaryData myPrimaryData;
    private FullGameObject myFullGameObject;
    private final String playerId = "root";
    private final String playerMoney = "20000";
    private final String playerDiamonds = "300";
    private static final String PLAYER_ID = "thisPlayerId";
    private static final String CARDS = "playersCards";
    private static final String[] playerSettings = {"myPlayer", "testRoot"};
    private static final String PLAYER_NAME = "thisPlayerId";

    private AppDatabase db;
    private SharedPreferences sharedPreferences;

    @Before
    public void initPrefObject(){
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .build();
        AppDelegate.setDatabase(db);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext());
        InitialProvider.init();
    }

    @Test
    public void getDefaultPlayerIdTest(){
        setTestPlayerId(null);
        assertTrue(InitialProvider.provideInitialObject().getPlayerId().equals(""));
    }

    @Test
    public void getPlayerIdTest(){
        setTestPlayerId(playerId);
        assertTrue(playerId.equals(InitialProvider.provideInitialObject().getPlayerId()));
    }

    @Test
    public void getPlayerIdObjectTest(){
        PlayerId myPlayerIdObject = new PlayerId(playerId);
        PlayerId playerIdObject = InitialProvider.provideInitialObject().getPlayerIdObject(playerId);
        assertTrue(myPlayerIdObject.equals(playerIdObject));
    }

    @Test //and setPrimaryDataTest()
    public void testSetNewPlayerData() {
        setTestPlayerObjects();
        InitialProvider.provideInitialObject().setNewPlayerData(newPlayerData);

        ChoiceDao choiceDao = db.choiceDao();
        PrimaryData primaryData = choiceDao.getPrimaryData();
        assertTrue(newPlayerData.getId().equals(getTestPlayerId()));
        assertTrue(newPlayerData.getAllFullCards().equals(getTestNewPlayerData()));
        assertTrue(myPrimaryData.equals(primaryData));
    }

    @Test
    public void setNamePlayerTest() {
        InitialProvider.provideInitialObject().setNamePlayer(playerSettings);
        assertTrue(playerSettings[1].equals(getTestNamePlayer()));
    }

    @Test
    public void setFullGameObjectTest(){
        setTestFullGameObject();
        InitialProvider.provideInitialObject().setFullGameObject(myFullGameObject);

        GameDao gameDao = db.gameDao();
        FullGameObject fullGameObject = gameDao.getFullGameObject();
        assertTrue(myFullGameObject.equals(fullGameObject));
    }

    private void setTestPlayerObjects(){
        allFullCards = new ArrayList<>();
        TreeSet<String> cards = new TreeSet<>();
        TreeSet<String> cards2 = new TreeSet<>();
        cards.add("21");
        cards.add("12");
        cards2.add("75");
        cards2.add("89");
        allFullCards.add(0, cards);
        allFullCards.add(1, cards2);
        newPlayerData = new NewPlayerData();
        newPlayerData.setId(playerId);
        newPlayerData.setAllFullCards(allFullCards);
        newPlayerData.setPlayerMoney(playerMoney);
        newPlayerData.setPlayerDiamonds(playerDiamonds);
        myPrimaryData = new PrimaryData(0,playerMoney, playerDiamonds);
    }

    private String getTestPlayerId(){
        return sharedPreferences.getString(PLAYER_ID, "");
    }

    private ArrayList<Set<String>> getTestNewPlayerData() {
        ArrayList<Set<String>> allGetFullCards = new ArrayList<>();
        for(int i = 0; i < allFullCards.size(); i++){
            allGetFullCards.add(i, sharedPreferences.getStringSet(CARDS + i, new TreeSet<>()));
        }
        return allGetFullCards;
    }

    private void setTestPlayerId(String playerId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_ID, playerId);
        editor.apply();
    }

    private String getTestNamePlayer() {
        return sharedPreferences.getString(PLAYER_NAME, "root");
    }

    private void setTestFullGameObject(){
        myFullGameObject = new FullGameObject();
        int[] s = {1};
        myFullGameObject.setIdsCards(s);
        myFullGameObject.setCrossedOutCells(playerSettings);
        myFullGameObject.setVisibleCask(playerSettings);
        List<OtherPlayers> allFullCards = new ArrayList<>();
        OtherPlayers otherPlayers = new OtherPlayers();
        otherPlayers.setNamePlayer(PLAYER_NAME);
        otherPlayers.setImagePlayer("myImage");
        allFullCards.add(0, otherPlayers);
        myFullGameObject.setOtherPlayersList(allFullCards);
        myFullGameObject.setGreenCells(playerSettings);
        myFullGameObject.setPlayerDiamonds(playerDiamonds);
    }
}
