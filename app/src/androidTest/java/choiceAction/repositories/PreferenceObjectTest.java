package choiceAction.repositories;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import forTest.RxSchedulersTestRule;
import my.game.loto.choiceAction.repository.RepositoryProvider;
import my.game.loto.choiceAction.retrofit.settingsObjects.StartingObject;

import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class PreferenceObjectTest {

    private String[] stringsPreferencesForGet;
    private String[] stringsPreferencesForSet;
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
        RepositoryProvider.providePreferenceObject().setPlayerName(null);
        String playerName = RepositoryProvider.providePreferenceObject().getPlayerName();
        assertTrue(myDefaultPlayerName.equals(playerName));
    }

    @Test
    public void testGetPlayerName(){
        RepositoryProvider.providePreferenceObject().setPlayerName(myPlayerName);
        String playerName = RepositoryProvider.providePreferenceObject().getPlayerName();
        assertTrue(myPlayerName.equals(playerName));
    }

    @Test
    public void testSharedPreferences() {
        RepositoryProvider.providePreferenceObject().setPlayerName(null);
        RepositoryProvider.providePreferenceObject().setPreferences(stringsPreferencesForSet);
        RepositoryProvider
                .providePreferenceObject()
                .getPreferences()
                .subscribe(preferencesObject ->
                        assertTrue(Arrays.equals(preferencesObject, stringsPreferencesForGet)));
    }

    @Test
    public void testGetStartingObject(){
        RepositoryProvider.providePreferenceObject().setPreferences(stringsPreferencesForSet);
        RepositoryProvider.providePreferenceObject().setIdStartingObject(idPlayer);
        StartingObject myStartingObject = new StartingObject(idPlayer, stringsPreferencesForSet);
        StartingObject arriveStartingObject = RepositoryProvider.providePreferenceObject().getStartingObject();
        assertTrue(myStartingObject.equals(arriveStartingObject));
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

}
