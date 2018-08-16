package repositories;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import forTest.RxSchedulersTestRule;
import my.game.loto.firstAction.repository.RepositoryProvider;
import my.game.loto.firstAction.retrofit.settingsObjects.PlayObject;
import retrofit2.adapter.rxjava.HttpException;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class ConnectingRepositoryTest {

    private static final int[] returnedIdsCards = {11,73,17};
    private static final String namePlayer = "root";
    private static final String imagePlayer = "https://docs.oracle.com";

    private static final int[] returnedIdsCards2 = {13,63,15};
    private static final String namePlayer2 = "root2";
    private static final String imagePlayer2 = "https://www.google.ru";

    private static final String myToken = "error";

    //Id and strings = null
    @Rule
    public RxSchedulersTestRule mRule = new RxSchedulersTestRule();

    @Before
    public void setConnectingRepository() {
        RepositoryProvider.init();
    }

    @Test
    public void testStartGame() throws Exception {
        List<PlayObject> listPlayObject = RepositoryProvider.provideConnectingRepository().startGame().toBlocking().first();

        assertTrue(Arrays.equals(returnedIdsCards, listPlayObject.get(0).getIdsCards()));
        assertTrue(namePlayer.equals(listPlayObject.get(0).getNamePlayer()));
        assertTrue(imagePlayer.equals(listPlayObject.get(0).getImagePlayer()));

        assertTrue(Arrays.equals(returnedIdsCards2, listPlayObject.get(1).getIdsCards()));
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

}
