package repositories;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import my.game.loto.firstAction.repository.RepositoryProvider;
import my.game.loto.firstAction.retrofit.SettingsObjects.StartingObject;
import my.game.loto.firstAction.screens.StartGameActivity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class PreferenceObjectTest {

    private String[] stringsPreferences;
    private static final String idPlayer = "myPlayerId";;

    @Rule
    public final ActivityTestRule<StartGameActivity> mainActivityRule = new ActivityTestRule<>(StartGameActivity.class);

    @Before
    public void setStrings(){
        stringsPreferences = new String[5];
        stringsPreferences[0] = "fast";
        stringsPreferences[1] = "short";
        stringsPreferences[2] = "close";
        stringsPreferences[3] = "four";
        stringsPreferences[4] = "200";
    }

    @Test
    public void testSharedPreferences() {
        RepositoryProvider.providePreferenceObject().setPreferences(stringsPreferences);
        RepositoryProvider
                .providePreferenceObject()
                .getPreferences()
                .subscribe(preferencesObject ->
                        assertEquals(preferencesObject, stringsPreferences));
    }

    @Test
    public void testGetStartingObject(){
        RepositoryProvider.providePreferenceObject().setStringsPreferences(stringsPreferences);
        RepositoryProvider.providePreferenceObject().setIdStartingObject(idPlayer);
        StartingObject myStartingObject = new StartingObject();
        myStartingObject.setId(idPlayer);
        myStartingObject.setStringsSettings(stringsPreferences);
        StartingObject arriveStartingObject = RepositoryProvider.providePreferenceObject().getStartingObject();
        assertTrue(myStartingObject.equals(arriveStartingObject));
    }

}
