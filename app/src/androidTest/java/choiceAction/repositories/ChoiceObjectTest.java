package choiceAction.repositories;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import forTest.RxSchedulersTestRule;
import my.game.loto.choiceAction.repository.RepositoryProvider;
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;

import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class ChoiceObjectTest {

    private String[] stringsPreferencesForGet;
    private String[] stringsPreferencesForSet;
    private String[] defaultStrings;
    private static final String idPlayer = "myPlayerId";
    private static final String myDefaultPlayerName = "root";
    private static final String myPlayerName = "rootRoot";

    @Rule
    public RxSchedulersTestRule mRule = new RxSchedulersTestRule();

    @Before
    public void initPrefObject(){
        RepositoryProvider.init();
        setStringsForSet();
        setStringsForGet();
    }

    @Test
    public void testDefaultGetPlayerName(){
        RepositoryProvider.provideChoiceObject().setPlayerName(null);
        String playerName = RepositoryProvider.provideChoiceObject().getPlayerName();
        assertTrue(myDefaultPlayerName.equals(playerName));
    }

    @Test
    public void testGetPlayerName(){
        RepositoryProvider.provideChoiceObject().setPlayerName(myPlayerName);
        String playerName = RepositoryProvider.provideChoiceObject().getPlayerName();
        assertTrue(myPlayerName.equals(playerName));
    }

    @Test
    public void testGetPreferences() {
        //RepositoryProvider.provideChoiceObject().setPlayerName(null);
        RepositoryProvider.provideChoiceObject().setPreferences(stringsPreferencesForSet);
        RepositoryProvider
                .provideChoiceObject()
                .getPreferences()
                .subscribe(preferencesObject ->
                        assertTrue(Arrays.equals(preferencesObject, stringsPreferencesForGet)));
    }

    @Test
    public void testDefaultGetPreferences() {
        //RepositoryProvider.provideChoiceObject().setPlayerName(null);
        setDefaultStrings();
        RepositoryProvider.provideChoiceObject().setPreferences(new String[5]);
        RepositoryProvider
                .provideChoiceObject()
                .getPreferences()
                .subscribe(preferencesObject ->
                        assertTrue(Arrays.equals(preferencesObject, defaultStrings)));
    }

    @Test
    public void testGetStartingObject(){
        RepositoryProvider.provideChoiceObject().setPreferences(stringsPreferencesForSet);
        RepositoryProvider.provideChoiceObject().setIdStartingObject(idPlayer);
        StartingObject myStartingObject = new StartingObject(idPlayer, stringsPreferencesForSet);
        StartingObject arriveStartingObject = RepositoryProvider.provideChoiceObject().getStartingObject();
        assertTrue(myStartingObject.equals(arriveStartingObject));
    }

    @Test
    public void testGetPrimaryData(){
        PrimaryData startingPrimaryData = new PrimaryData(null, null);
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("PrimaryData.out"))){
            output.writeObject(startingPrimaryData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrimaryData returnedPrimaryData = RepositoryProvider.provideChoiceObject().getPrimaryData();
        assertTrue(startingPrimaryData.equals(returnedPrimaryData));
    }

    @Test
    public void testSetListPlayObjects(){
        PlayObject playObject = new PlayObject();
        playObject.setNamePlayer(myDefaultPlayerName);
        List<PlayObject> listPlayObjects = new ArrayList<>();
        listPlayObjects.add(playObject);
        RepositoryProvider.provideChoiceObject().setListPlayObjects(listPlayObjects);

        try (ObjectInputStream output = new ObjectInputStream(new FileInputStream("StartObjects.out"));) {
            listPlayObjects = (List<PlayObject>) output.readObject();
        } catch (ClassNotFoundException | IOException e) {
            //TODO with log4j
            e.printStackTrace();
        }
        assertTrue(playObject.equals(listPlayObjects.get(0)));
    }

    private void setStringsForSet() {
        stringsPreferencesForSet = new String[5];
        stringsPreferencesForSet[0] = "fast";
        stringsPreferencesForSet[1] = "short";
        stringsPreferencesForSet[2] = "close";
        stringsPreferencesForSet[3] = "four";
        stringsPreferencesForSet[4] = "200";
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
