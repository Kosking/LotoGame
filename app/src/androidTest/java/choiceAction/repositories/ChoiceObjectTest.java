package choiceAction.repositories;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<PlayObject> myListPlayObjects;
    private String[] stringsPreferences;
    private String[] defaultStringsPreferences;
    private static final String ID_PLAYER = "myPlayerId";
    private static final String MY_DEFAULT_NAME = "root";
    private static final String MY_PLAYER_NAME = "rootRoot";
    private static final String PLAYER_NAME = "thisPlayerId";
    private static final String PLAYER_ID = "thisPlayerId";
    private static final String NUMBER_OF_PLAYERS = "numberOfPlayers";
    private static final String LIST_PLAY_TOKEN = "listPlayObject";

    private AppDatabase db;
    private GameDao gameDao;
    private SharedPreferences sharedPreferences;

    @Before
    public void init(){
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .build();
        AppDelegate.setDatabase(db);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext());
        RepositoryProvider.init();
    }

    @Test
    public void getStartObjectTest(){
        setStartObject(MY_PLAYER_NAME);
        RepositoryProvider
                .provideChoiceObject()
                .getStartObject()
                .subscribe(startObject -> assertTrue(myStartObject.equals(startObject)));
    }

    @Test
    public void getDefaultStartObjectTest(){
        setStartObject(MY_DEFAULT_NAME);
        RepositoryProvider
                .provideChoiceObject()
                .getStartObject()
                .subscribe(startObject -> assertTrue(myStartObject.equals(startObject)));
    }

    @Test
    public void getPreferencesTest() {
        setStringsPreferences();
        RepositoryProvider.provideChoiceObject().setPreferences(stringsPreferences);
        RepositoryProvider
                .provideChoiceObject()
                .getPreferences()
                .subscribe(preferencesObject ->
                        assertTrue(Arrays.equals(preferencesObject, stringsPreferences)));
    }

    @Test
    public void defaultGetPreferencesTest() {
        setDefaultStringsPreferences();
        RepositoryProvider.provideChoiceObject().setPreferences(defaultStringsPreferences);
        RepositoryProvider
                .provideChoiceObject()
                .getPreferences()
                .subscribe(preferencesObject ->
                        assertTrue(Arrays.equals(preferencesObject, defaultStringsPreferences)));
    }

    @Test
    public void getStartingObjectTest(){
        setIdStartingObject(ID_PLAYER);
        String[] preferences = new String[3];
        StartingObject myStartingObject = new StartingObject(ID_PLAYER, preferences);

        StartingObject startingObject = RepositoryProvider.provideChoiceObject().getStartingObject(preferences);
        assertTrue(myStartingObject.equals(startingObject));
    }

    @Test
    public void getDefaultStartingObjectTest(){
        setIdStartingObject(null);
        String[] preferences = new String[3];
        StartingObject myStartingObject = new StartingObject("", preferences);

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
        assertTrue(getListPlayToken().equals("true"));
    }

    @After
    public void closeDb() throws Exception {
        db.close();
    }

    private void setStartObject(String playerName) {
        Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_NAME, playerName);
        editor.apply();

        PrimaryData primaryData = new PrimaryData(0, MY_DEFAULT_NAME, MY_DEFAULT_NAME);
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
        playObject.setId(0);
        playObject.setNamePlayer(MY_PLAYER_NAME);
        playObject.setIdsCards(new int[2]);
        playObject.setImagePlayer("myImage");
        playObject.setPlayerDiamonds("1000");
        playObject.setStart("true");
        playObject.setNamePlayer(MY_DEFAULT_NAME);
        myListPlayObjects = new ArrayList<>();
        myListPlayObjects.add(playObject);
    }

    private String getListPlayToken(){
        return sharedPreferences.getString(LIST_PLAY_TOKEN, "");
    }

    public int[] getNumberOfPlayers() {
        int lastNumberOfPlayer = sharedPreferences.getInt(NUMBER_OF_PLAYERS, 0);
        int[] numberOfPlayers = new int[lastNumberOfPlayer];
        for (int i = 0; i < lastNumberOfPlayer; i++){
            numberOfPlayers[i] = i;
        }
        return numberOfPlayers;
    }

    private void setStringsPreferences() {
        stringsPreferences = new String[5];
        stringsPreferences[0] = "fast";
        stringsPreferences[1] = "short";
        stringsPreferences[2] = "close";
        stringsPreferences[3] = "four";
        stringsPreferences[4] = "200";
    }

    private void setDefaultStringsPreferences() {
        defaultStringsPreferences = new String[5];
        defaultStringsPreferences[0] = "slow";
        defaultStringsPreferences[1] = "short";
        defaultStringsPreferences[2] = "open";
        defaultStringsPreferences[3] = "two";
        defaultStringsPreferences[4] = "100";
    }
}
