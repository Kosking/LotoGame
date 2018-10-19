package choiceAction.repositories;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import forTest.RxSchedulersTestRule;
import my.game.loto.AppDelegate;
import my.game.loto.choiceAction.repository.RepositoryProvider;
import my.game.loto.choiceAction.repository.StartObject;
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject;
import my.game.loto.gameAction.repository.room.GameDao;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
import my.game.loto.room.AppDatabase;

import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class ChoiceObjectTest {

    private StartObject myStartObject;
    List<PlayObject> myListPlayObjects;
    private String[] stringsPreferencesForGet;
    private String[] defaultStrings;
    private static final String idPlayer = "myPlayerId";
    private static final String myDefaultName = "root";
    private static final String myPlayerName = "rootRoot";


    private static final String PLAYER_NAME = "thisPlayerId";
    private static final String PLAYER_ID = "thisPlayerId";
    private static final String NUMBER_OF_PLAYERS = "numberOfPlayers";
    private AppDatabase db;
    private GameDao gameDao;
    private SharedPreferences sharedPreferences;

    @Rule
    public RxSchedulersTestRule mRule = new RxSchedulersTestRule();

    @Before
    public void init(){
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .build();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext());
        RepositoryProvider.init();
    }

    @Test
    public void getStartObjectTest(){
        setStartObject(myPlayerName);
        RepositoryProvider
                .provideChoiceObject()
                .getStartObject()
                .subscribe(startObject -> assertTrue(myStartObject.equals(startObject)));
    }

    @Test
    public void getDefaultStartObjectTest(){
        setStartObject(myDefaultName);
        RepositoryProvider
                .provideChoiceObject()
                .getStartObject()
                .subscribe(startObject -> assertTrue(myStartObject.equals(startObject)));
    }

    @Test
    public void getPreferencesTest() {
        setStringsForGet();
        RepositoryProvider.provideChoiceObject().setPreferences(stringsPreferencesForGet);
        RepositoryProvider
                .provideChoiceObject()
                .getPreferences()
                .subscribe(preferencesObject ->
                        assertTrue(Arrays.equals(preferencesObject, stringsPreferencesForGet)));
    }

    @Test
    public void defaultGetPreferencesTest() {
        setDefaultStrings();
        RepositoryProvider.provideChoiceObject().setPreferences(new String[5]);
        RepositoryProvider
                .provideChoiceObject()
                .getPreferences()
                .subscribe(preferencesObject ->
                        assertTrue(Arrays.equals(preferencesObject, defaultStrings)));
    }

    @Test
    public void getStartingObjectTest(){
        setIdStartingObject(idPlayer);
        String[] preferences = new String[3];
        StartingObject myStartingObject = new StartingObject(idPlayer, preferences);
        StartingObject startingObject = RepositoryProvider.provideChoiceObject().getStartingObject(preferences);
        assertTrue(myStartingObject.equals(startingObject));
    }

    @Test
    public void getDefaultStartingObjectTest(){
        setIdStartingObject(null);
        String[] preferences = new String[3];
        StartingObject myStartingObject = new StartingObject(idPlayer, preferences);
        StartingObject startingObject = RepositoryProvider.provideChoiceObject().getStartingObject(preferences);
        assertTrue(myStartingObject.equals(startingObject));
    }

    @Test
    public void setListPlayObjectsTest(){
        setListPlayObjects();
        RepositoryProvider.provideChoiceObject().setListPlayObjects(myListPlayObjects);

        gameDao = db.gameDao();
        List<PlayObject> listPlayObjects = gameDao.getListPlayObjects(getNumberOfPlayers());
        assertTrue(myListPlayObjects.equals(listPlayObjects));
    }

    @After
    public void closeDb() throws Exception {
        db.close();
    }

    private void setStartObject(String playerName) {
        Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_NAME, playerName);
        editor.apply();

        PrimaryData primaryData = new PrimaryData(0, myDefaultName, myDefaultName);
        gameDao = db.gameDao();
        gameDao.setPrimaryData(primaryData);

        myStartObject = new StartObject(playerName, primaryData);
    }

    public void setIdStartingObject(String idPlayer) {
        Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_ID, idPlayer);
        editor.apply();
    }

    private void setListPlayObjects() {
        PlayObject playObject = new PlayObject();
        playObject.setNamePlayer(myDefaultName);
        myListPlayObjects = new ArrayList<>();
        myListPlayObjects.add(playObject);
    }

    public int[] getNumberOfPlayers() {
        int lastNumberOfPlayer = sharedPreferences.getInt(NUMBER_OF_PLAYERS, 0);
        int[] numberOfPlayers = new int[lastNumberOfPlayer];
        for (int i = 0; i < lastNumberOfPlayer; i++){
            numberOfPlayers[i] = i;
        }
        return numberOfPlayers;
    }

    private void setStringsForGet() {
        stringsPreferencesForGet = new String[6];
        stringsPreferencesForGet[0] = "fast";
        stringsPreferencesForGet[1] = "short";
        stringsPreferencesForGet[2] = "close";
        stringsPreferencesForGet[3] = "four";
        stringsPreferencesForGet[4] = "200";
        stringsPreferencesForGet[5] = "root";
    }

    private void setDefaultStrings() {
        defaultStrings = new String[5];
        defaultStrings[0] = "slow";
        defaultStrings[1] = "short";
        defaultStrings[2] = "open";
        defaultStrings[3] = "two";
        defaultStrings[4] = "100";
    }
}
