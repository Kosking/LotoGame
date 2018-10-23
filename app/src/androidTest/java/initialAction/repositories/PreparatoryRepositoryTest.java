package initialAction.repositories;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import forTest.RxSchedulersTestRule;
import my.game.loto.choiceAction.retrofit.TestToken;
import my.game.loto.initialAction.repository.InitialProvider;
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.OtherPlayers;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerToken;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
import retrofit2.adapter.rxjava.HttpException;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class PreparatoryRepositoryTest {

    private static final String myToken = "true";
    private static final String myErrorToken = "error";
    private static final int[] idsCards = {11, 73, 17};
    private static final String[] crossedOutCells = {"45","9","67"};
    private static final String[] greenCells = {"32","89","15"};
    private static final String[] visibleCask = {"45","9","67"};
    private static final String playerDiamonds = "20";
    private static final String namePlayer = "root";
    private static final String[] playerSettings = {"myImage", "root"};
    private static final String playerMoney = "15000";
    private static final String myPlayerDiamonds = "200";
    private static final String playerId = "root";
    private static final int playerIdToInt = 1;

    private FullGameObject myFullGameObject;
    private NewPlayerData myNewPlayerData;

    @Rule
    public RxSchedulersTestRule mRule = new RxSchedulersTestRule();

    @Before
    public void setConnectingRepository() {
        InitialProvider.init();
        InitialProvider.providePreparatoryRepository().setPlayerIdObject(namePlayer);
    }

    @Test
    public void getPlayerGameTokenTest(){
        PlayerToken playerToken = InitialProvider.providePreparatoryRepository()
                .getPlayerGameToken().toBlocking().first();

        assertTrue(myToken.equals(playerToken.getToken()));
    }

    @Test
    public void errorGetPlayerGameTokenTest(){
        TestToken.setTestToken(myErrorToken);

        TestSubscriber<PlayerToken> testSubscriber = new TestSubscriber<>();
        InitialProvider.providePreparatoryRepository()
                .getPlayerGameToken().subscribe(testSubscriber);

        testSubscriber.assertError(HttpException.class);
    }

    @Test
    public void getPlayDataTest(){
        setFullGameObject();
        FullGameObject fullGameObject = InitialProvider.providePreparatoryRepository()
               .getPlayData().toBlocking().first();

        assertTrue(myFullGameObject.equals(fullGameObject));
    }

    @Test
    public void errorGetPlayDataTest(){
        TestToken.setTestToken(myErrorToken);

        TestSubscriber<FullGameObject> testSubscriber = new TestSubscriber<>();
        InitialProvider.providePreparatoryRepository().getPlayData().subscribe(testSubscriber);

        testSubscriber.assertError(HttpException.class);
    }

    @Test
    public void getPrimaryDataTest(){
        PrimaryData myPrimaryData = new PrimaryData(0, playerMoney, myPlayerDiamonds);
        PrimaryData primaryData = InitialProvider.providePreparatoryRepository()
                .getPrimaryData().toBlocking().first();

        assertTrue(myPrimaryData.equals(primaryData));
    }

    @Test
    public void errorGetPrimaryDataTest(){
        TestToken.setTestToken(myErrorToken);

        TestSubscriber<PrimaryData> testSubscriber = new TestSubscriber<>();
        InitialProvider.providePreparatoryRepository().getPrimaryData().subscribe(testSubscriber);

        testSubscriber.assertError(HttpException.class);
    }

    @Test
    public void createNewPlayerTest(){
        setNewPlayerData();
        NewPlayerData newPlayerData = InitialProvider.providePreparatoryRepository()
                .createNewPlayer(playerSettings).toBlocking().first();

        assertTrue(myNewPlayerData.equals(newPlayerData));
    }

    @Test
    public void errorCreateNewPlayerTest(){
        TestToken.setTestToken(myErrorToken);

        TestSubscriber<NewPlayerData> testSubscriber = new TestSubscriber<>();
        InitialProvider.providePreparatoryRepository()
                .createNewPlayer(playerSettings).subscribe(testSubscriber);

        testSubscriber.assertError(HttpException.class);
    }

    @After
    public void setTrueTestToken() {
        TestToken.setTestToken("");
    }

    private void setFullGameObject(){
        myFullGameObject = new FullGameObject();
        myFullGameObject.setId(playerIdToInt);
        myFullGameObject.setIdsCards(idsCards);
        myFullGameObject.setCrossedOutCells(crossedOutCells);
        myFullGameObject.setGreenCells(greenCells);
        myFullGameObject.setVisibleCask(visibleCask);
        myFullGameObject.setOtherPlayersList(setOtherPlayersList());
        myFullGameObject.setPlayerDiamonds(playerDiamonds);
    }

    private List<OtherPlayers> setOtherPlayersList(){
        List<OtherPlayers> otherPlayersList = new ArrayList<>();

        OtherPlayers otherPlayers = new OtherPlayers();
        otherPlayers.setNamePlayer(namePlayer);
        otherPlayers.setImagePlayer(namePlayer);
        OtherPlayers otherPlayers2 = new OtherPlayers();
        otherPlayers2.setNamePlayer(namePlayer);
        otherPlayers2.setImagePlayer(namePlayer);

        otherPlayersList.add(otherPlayers);
        otherPlayersList.add(otherPlayers2);
        return otherPlayersList;
    }

    private void setNewPlayerData(){
        ArrayList<Set<String>> allFullCards = new ArrayList<>();
        TreeSet<String> cards = new TreeSet<>();
        TreeSet<String> cards2 = new TreeSet<>();
        cards.add("21");
        cards.add("12");
        cards2.add("75");
        cards2.add("89");
        allFullCards.add(0, cards);
        allFullCards.add(1, cards2);
        myNewPlayerData = new NewPlayerData();
        myNewPlayerData.setId(playerId);
        myNewPlayerData.setAllFullCards(allFullCards);
        myNewPlayerData.setPlayerDiamonds(myPlayerDiamonds);
        myNewPlayerData.setPlayerMoney(playerMoney);

        /*Gson gson = new Gson();
        String string = gson.toJson(newPlayerData);
        Log.d("Kostya", string);*/
    }
}