package initialAction.repositories;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import my.game.loto.AppDelegate;
import my.game.loto.initialAction.repository.InitialProvider;
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerSettings;
import my.game.loto.initialAction.retrofit.settingsObjects.OtherPlayers;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerId;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class InitialObjectTest {

    private SharedPreferences sharedPreferences;

    private NewPlayerData newPlayerData;
    private List<String> allFullCards;
    private PrimaryData primaryData;
    private FullGameObject fullGameObject;
    private final String playerId = "root";
    private final String playerMoney = "20000";
    private final String playerDiamonds = "300";
    private static final String PLAYER_ID = "thisPlayerId";
    private static final String CARDS = "playersCards";
    private static final String[] playerSettings = {"myPlayer", "testRoot"};
    private static final String PLAYER_NAME = "thisPlayerId";

    @Before
    public void initPrefObject(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AppDelegate.getContext());
        InitialProvider.init();
    }

    @Test
    public void getDefaultPlayerIdTest(){
        NewPlayerData newPlayerData = new NewPlayerData();
        newPlayerData.setId(null);

        setTestPlayerId(newPlayerData);
        assertTrue(InitialProvider.provideInitialObject().getPlayerId().equals(""));
    }

    @Test
    public void getPlayerIdTest(){
        NewPlayerData newPlayerData = new NewPlayerData();
        newPlayerData.setId(playerId);

        setTestPlayerId(newPlayerData);
        assertTrue(playerId.equals(InitialProvider.provideInitialObject().getPlayerId()));
    }

    @Test
    public void getPlayerIdObjectTest(){
        PlayerId playerIdObject = new PlayerId(playerId);
        assertTrue(playerIdObject.equals(InitialProvider.provideInitialObject().getPlayerIdObject(playerId)));
    }

    @Test //and setPrimaryDataTest()
    public void testSetNewPlayerData() {
        setTestPlayerObjects();
        InitialProvider.provideInitialObject().setNewPlayerData(newPlayerData);

        assertTrue(newPlayerData.getId().equals(getTestNewPlayerData().getId()));
        assertTrue(newPlayerData.getAllFullCards().equals(getTestNewPlayerData().getAllFullCards()));
        assertTrue(primaryData.equals(getTestPrimaryData()));
    }


    @Test
    public void  getPlayerSettingsObjectTest(){
        String[] playerSettings = {"two", "root"};
        NewPlayerSettings newPlayerSettings = new NewPlayerSettings(playerSettings[0], playerSettings[1]);
        assertTrue(newPlayerSettings.equals(InitialProvider.provideInitialObject().getPlayerSettingsObject(playerSettings)));
    }

    @Test
    public void setNamePlayerTest() {
        InitialProvider.provideInitialObject().setNamePlayer(playerSettings);
        assertTrue(playerSettings[1].equals(getTestNamePlayer()));
    }

    @Test
    public void setFullGameObjectTest(){
        setTestFullGameObject();
        InitialProvider.provideInitialObject().setFullGameObject(fullGameObject);

        assertTrue(fullGameObject.equals(getTestFullGameObject()));
    }

    private void setTestPlayerObjects(){
        allFullCards = new ArrayList<>();
        allFullCards.add(0,"11");
        allFullCards.add(1,"73");
        newPlayerData = new NewPlayerData();
        newPlayerData.setId(playerId);
        newPlayerData.setAllFullCards(allFullCards);
        newPlayerData.setPlayerMoney(playerMoney);
        newPlayerData.setPlayerDiamonds(playerDiamonds);
        primaryData = new PrimaryData(playerMoney, playerDiamonds);
    }

    private NewPlayerData getTestNewPlayerData() {
        NewPlayerData newPlayerData = new NewPlayerData();
        newPlayerData.setId(sharedPreferences.getString(PLAYER_ID, ""));
        List<String> allGetFullCards = new ArrayList<>();
        for(int i = 0; i < allFullCards.size(); i++){
            allGetFullCards.add(i, sharedPreferences.getString(CARDS + i, ""));
        }
        newPlayerData.setAllFullCards(allGetFullCards);
        return newPlayerData;
    }

    private PrimaryData getTestPrimaryData() {
        PrimaryData primaryData = null;
        try (ObjectInputStream output = new ObjectInputStream(new FileInputStream("PrimaryData.out"));) {
            primaryData = (PrimaryData) output.readObject();
        } catch (ClassNotFoundException | IOException e) {
            //TODO with log4j
            e.printStackTrace();
        }
        return primaryData;
    }

    private void setTestPlayerId(NewPlayerData playerData) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER_ID, playerData.getId());
        editor.apply();
    }

    private String getTestNamePlayer() {
        return sharedPreferences.getString(PLAYER_NAME, "root");
    }

    private void setTestFullGameObject(){
        fullGameObject = new FullGameObject();
        int[] s = {1};
        fullGameObject.setIdsCards(s);
        fullGameObject.setCrossedOutCells(playerSettings);
        fullGameObject.setVisibleCask(playerSettings);
        List<OtherPlayers> allFullCards = new ArrayList<>();
        allFullCards.add(0,new OtherPlayers());
        fullGameObject.setOtherPlayersList(allFullCards);
        fullGameObject.setGreenCells(playerSettings);
        fullGameObject.setPlayerDiamonds(playerDiamonds);
    }

    private FullGameObject getTestFullGameObject() {
        FullGameObject fullGameObject = null;
        try (ObjectInputStream output = new ObjectInputStream(new FileInputStream("FullGameObject.out"))) {
            fullGameObject = (FullGameObject) output.readObject();
        } catch (ClassNotFoundException | IOException e) {
            //TODO with log4j
            e.printStackTrace();
        }
        return fullGameObject;
    }
}
