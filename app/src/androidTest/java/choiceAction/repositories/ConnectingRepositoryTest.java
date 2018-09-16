package choiceAction.repositories;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import forTest.RxSchedulersTestRule;
import my.game.loto.choiceAction.repository.RepositoryProvider;
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import retrofit2.adapter.rxjava.HttpException;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class ConnectingRepositoryTest {

    private static final int[] myReturnedIdsCards = {11,73,17};
    private static final String namePlayer = "root";
    private static final String imagePlayer = "https://docs.oracle.com";

    //returnedIdsCards2 = null
    private static final String namePlayer2 = "root2";
    private static final String imagePlayer2 = "https://www.google.ru";

    private static final String myToken = "error";

    //StartObject: Id and strings = null
    @Rule
    public RxSchedulersTestRule mRule = new RxSchedulersTestRule();

    @Before
    public void setConnectingRepository() {
        RepositoryProvider.init();
    }

    @Test
    public void testStartGame() throws Exception {
        List<PlayObject> listPlayObject = RepositoryProvider.provideConnectingRepository().startGame().toBlocking().first();

        assertTrue(Arrays.equals(myReturnedIdsCards, listPlayObject.get(0).getIdsCards()));
        assertTrue(namePlayer.equals(listPlayObject.get(0).getNamePlayer()));
        assertTrue(imagePlayer.equals(listPlayObject.get(0).getImagePlayer()));

        assertTrue(namePlayer2.equals(listPlayObject.get(1).getNamePlayer()));
        assertTrue(imagePlayer2.equals(listPlayObject.get(1).getImagePlayer()));
    }

    @Test
    public void testErrorStartGame() throws Exception {
        RepositoryProvider.providePreferenceObject().setTestToken(myToken);

        TestSubscriber <List<PlayObject>> testSubscriber = new TestSubscriber<>();
        RepositoryProvider.provideConnectingRepository().startGame().subscribe(testSubscriber);

        testSubscriber.assertError(HttpException.class);
    }

    @Test
    public void test(){
        List<PlayObject> playObjectList = new ArrayList<>();

        PlayObject playObject = new PlayObject();
        playObject.setIdsCards(myReturnedIdsCards);
        playObject.setNamePlayer(namePlayer);
        playObject.setImagePlayer(imagePlayer);

        PlayObject playObject2 = new PlayObject();
        //playObject2.setIdsCards(null);
        playObject2.setNamePlayer(namePlayer2);
        playObject2.setImagePlayer(imagePlayer2);

        playObjectList.add(playObject);
        playObjectList.add(playObject2);

        Gson gson = new Gson();
        String string = gson.toJson(playObjectList);
        Log.d("Kostya", string);
        /*JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stringsSettings", Arrays.toString(setStringsStartingObject));
        jsonObject.addProperty("playerId", Arrays.toString(setStringsStartingObject));
        return jsonObject;*/

    }

}
