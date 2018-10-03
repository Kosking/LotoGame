package initialAction.repositories;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import forTest.RxSchedulersTestRule;
import my.game.loto.initialAction.repository.InitialProvider;
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.OtherPlayers;
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
    private static final String[] visibleCask = {"45","9","67"};;
    private List<OtherPlayers> otherPlayersList;
    private static final String playerDiamonds = "20";
    private String namePlayer = "root";

    private static final String[] playerSettings = {"myImage", "root"};

    private static final String playerMoney = "15000";
    private static final String myPlayerDiamonds = "200";
    private static final String playerId = "root";

    @Rule
    public RxSchedulersTestRule mRule = new RxSchedulersTestRule();

    @Before
    public void setConnectingRepository() {
        InitialProvider.init();
        InitialProvider.providePreparatoryRepository().setPlayerIdObject(namePlayer);
    }

    @Test
    public void getPlayerGameTokenTest(){
        String playerToken = InitialProvider.providePreparatoryRepository().getPlayerGameToken().toBlocking().first();

        assertTrue(myToken.equals(playerToken));

    }

    @Test
    public void errorGetPlayerGameTokenTest(){
        InitialProvider.provideInitialObject().setTestToken(myErrorToken);

        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        InitialProvider.providePreparatoryRepository().getPlayerGameToken().subscribe(testSubscriber);

        testSubscriber.assertError(HttpException.class);
    }

    @Test
    public void getPlayDataTest(){
       FullGameObject fullGameObject = InitialProvider.providePreparatoryRepository().getPlayData().toBlocking().first();

       setOtherPlayersList();

       assertTrue(Arrays.equals(idsCards, fullGameObject.getIdsCards()));
       assertTrue(Arrays.equals(crossedOutCells, fullGameObject.getCrossedOutCells()));
       assertTrue(Arrays.equals(greenCells, fullGameObject.getGreenCells()));
       assertTrue(Arrays.equals(visibleCask, fullGameObject.getVisibleCask()));
       assertTrue(otherPlayersList.get(0).equals(fullGameObject.getOtherPlayersList().get(0)));
       assertTrue(otherPlayersList.get(1).equals(fullGameObject.getOtherPlayersList().get(1)));
       assertTrue(playerDiamonds.equals(fullGameObject.getPlayerDiamonds()));
    }

    @Test
    public void errorGetPlayDataTest(){
        InitialProvider.provideInitialObject().setTestToken(myErrorToken);

        TestSubscriber<FullGameObject> testSubscriber = new TestSubscriber<>();
        InitialProvider.providePreparatoryRepository().getPlayData().subscribe(testSubscriber);

        testSubscriber.assertError(HttpException.class);
    }

    @Test
    public void getPrimaryDataTest(){
        PrimaryData primaryData = InitialProvider.providePreparatoryRepository().getPrimaryData().toBlocking().first();

        assertTrue(myPlayerDiamonds.equals(primaryData.getPlayerDiamonds()));
        assertTrue(playerMoney.equals(primaryData.getPlayerMoney()));
    }

    @Test
    public void errorGetPrimaryDataTest(){
        InitialProvider.provideInitialObject().setTestToken(myErrorToken);

        TestSubscriber<PrimaryData> testSubscriber = new TestSubscriber<>();
        InitialProvider.providePreparatoryRepository().getPrimaryData().subscribe(testSubscriber);

        testSubscriber.assertError(HttpException.class);
    }

    @Test
    public void createNewPlayerTest(){
        NewPlayerData newPlayerData = InitialProvider.providePreparatoryRepository().createNewPlayer(playerSettings).toBlocking().first();

        assertTrue(playerId.equals(newPlayerData.getId()));
        assertTrue(myPlayerDiamonds.equals(newPlayerData.getPlayerDiamonds()));
        assertTrue(playerMoney.equals(newPlayerData.getPlayerMoney()));
    }

    @Test
    public void errorCreateNewPlayerTest(){
        InitialProvider.provideInitialObject().setTestToken(myErrorToken);

        TestSubscriber<NewPlayerData> testSubscriber = new TestSubscriber<>();
        InitialProvider.providePreparatoryRepository().createNewPlayer(playerSettings).subscribe(testSubscriber);

        testSubscriber.assertError(HttpException.class);
    }

    private void setOtherPlayersList(){
        otherPlayersList = new ArrayList<>();

        OtherPlayers otherPlayers = new OtherPlayers();
        otherPlayers.setNamePlayer(namePlayer);
        otherPlayers.setImagePlayer(namePlayer);
        OtherPlayers otherPlayers2 = new OtherPlayers();
        otherPlayers2.setNamePlayer(namePlayer);
        otherPlayers2.setImagePlayer(namePlayer);

        otherPlayersList.add(otherPlayers);
        otherPlayersList.add(otherPlayers2);
    }


    /*@Test
    public void createJsonFullGameObject(){
        List<OtherPlayers> otherPlayersList = new ArrayList<>();

        OtherPlayers otherPlayers = new OtherPlayers();
        otherPlayers.setNamePlayer(namePlayer);
        otherPlayers.setImagePlayer(namePlayer);
        OtherPlayers otherPlayers2 = new OtherPlayers();
        otherPlayers2.setNamePlayer(namePlayer);
        otherPlayers2.setImagePlayer(namePlayer);

        otherPlayersList.add(otherPlayers);
        otherPlayersList.add(otherPlayers2);

        FullGameObject fullGameObject = new FullGameObject();
        fullGameObject.setIdsCards(idsCards);
        fullGameObject.setCrossedOutCells(crossedOutCells);
        fullGameObject.setGreenCells(greenCells);
        fullGameObject.setVisibleCask(visibleCask);
        fullGameObject.setOtherPlayersList(otherPlayersList);
        fullGameObject.setPlayerDiamonds(playerDiamonds);

        Gson gson = new Gson();
        String string = gson.toJson(fullGameObject);
        Log.d("Kostya", string);
    }

    @Test
    public void createJsonPrimaryData(){
        PrimaryData primaryData = new PrimaryData();

        primaryData.setPlayerDiamonds(myPlayerDiamonds);
        primaryData.setPlayerMoney(playerMoney);

        Gson gson = new Gson();
        String string = gson.toJson(primaryData);
        Log.d("Kostya", string);
    }

    @Test
    public void createNewPlayerData(){
        NewPlayerData newPlayerData = new NewPlayerData();

        newPlayerData.setId(playerId);
        newPlayerData.setPlayerDiamonds(myPlayerDiamonds);
        newPlayerData.setPlayerMoney(playerMoney);

        Gson gson = new Gson();
        String string = gson.toJson(newPlayerData);
        Log.d("Kostya", string);
    }*/
}
