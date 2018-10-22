package choiceAction.repositories;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import forTest.RxSchedulersTestRule;
import my.game.loto.choiceAction.repository.RepositoryProvider;
import my.game.loto.choiceAction.retrofit.TestToken;
import my.game.loto.choiceAction.retrofit.settingsObjects.PlayObject;
import retrofit2.adapter.rxjava.HttpException;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class ConnectingRepositoryTest {


    private static final int firstObjectId = 1;
    private static final int secondObjectId = 2;
    private static final int[] myReturnedIdsCards = {11,73,17};
    private static final String namePlayer = "root";
    private static final String imagePlayer = "https://docs.oracle.com";
    private static final String playerDiamonds = "2000";
    private static final String start = "2000";

    private static final String namePlayer2 = "root2";
    private static final String imagePlayer2 = "https://www.google.ru";

    private List<PlayObject> myListPlayObject;
    private static final String myToken = "error";

    @Rule
    public RxSchedulersTestRule mRule = new RxSchedulersTestRule();

    @Before
    public void setConnectingRepository() {
        RepositoryProvider.init();
    }

    @Test
    public void StartGameTest() throws Exception {
        setPlayObjectList();
        List<PlayObject> listPlayObject = RepositoryProvider.provideConnectingRepository()
                .startGame(null).toBlocking().first();

        assertTrue(myListPlayObject.equals(listPlayObject));
    }

    @Test
    public void errorStartGameTest() throws Exception {
        TestToken.setTestToken(myToken);

        TestSubscriber <List<PlayObject>> testSubscriber = new TestSubscriber<>();
        RepositoryProvider.provideConnectingRepository()
                .startGame(null).subscribe(testSubscriber);

        testSubscriber.assertError(HttpException.class);
    }

    private void setPlayObjectList(){
        PlayObject playObject = new PlayObject();
        playObject.setId(firstObjectId);
        playObject.setIdsCards(myReturnedIdsCards);
        playObject.setNamePlayer(namePlayer);
        playObject.setImagePlayer(imagePlayer);
        playObject.setPlayerDiamonds(playerDiamonds);
        playObject.setStart(start);

        PlayObject playObject2 = new PlayObject();
        playObject2.setId(secondObjectId);
        playObject2.setIdsCards(new int[1]);
        playObject2.setNamePlayer(namePlayer2);
        playObject2.setImagePlayer(imagePlayer2);
        playObject2.setPlayerDiamonds("null");
        playObject2.setStart("null");

        myListPlayObject = new ArrayList<>();
        myListPlayObject.add(playObject);
        myListPlayObject.add(playObject2);

        /*Gson gson = new Gson();
        String string = gson.toJson(myListPlayObject);
        Log.d("Kostya", string);*/
    }

}
