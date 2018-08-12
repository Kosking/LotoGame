package repositories;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import forTest.RxSchedulersTestRule;
import my.game.loto.firstAction.repository.RepositoryProvider;
import my.game.loto.firstAction.retrofit.settingsObjects.StartingObject;

import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class PreferenceObjectTest {

    private String[] stringsPreferences;
    private static final String idPlayer = "myPlayerId";



    @Rule
    public RxSchedulersTestRule mRule = new RxSchedulersTestRule();

    @Before
    public void initPrefObject(){
        RepositoryProvider.init();
        setStrings();
    }


    @Test
    public void testSharedPreferences() {
        RepositoryProvider.providePreferenceObject().setPreferences(stringsPreferences);
        RepositoryProvider
                .providePreferenceObject()
                .getPreferences()
                .subscribe(preferencesObject ->
                        assertTrue(Arrays.equals(preferencesObject, stringsPreferences)));
    }

    @Test
    public void testGetStartingObject(){
        RepositoryProvider.providePreferenceObject().setPreferences(stringsPreferences);
        RepositoryProvider.providePreferenceObject().setIdStartingObject(idPlayer);
        StartingObject myStartingObject = new StartingObject(idPlayer,stringsPreferences);
        StartingObject arriveStartingObject = RepositoryProvider.providePreferenceObject().getStartingObject();
        assertTrue(myStartingObject.equals(arriveStartingObject));
    }


    private void setStrings() {
        stringsPreferences = new String[5];
        stringsPreferences[0] = "fast";
        stringsPreferences[1] = "short";
        stringsPreferences[2] = "close";
        stringsPreferences[3] = "four";
        stringsPreferences[4] = "200";
    }

}
