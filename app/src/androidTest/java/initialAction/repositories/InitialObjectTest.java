package initialAction.repositories;

import android.content.SharedPreferences;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import my.game.loto.initialAction.repository.InitialProvider;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerSettings;
import my.game.loto.initialAction.retrofit.settingsObjects.PlayerId;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class InitialObjectTest {

    private SharedPreferences sharedPreferences;

    private final String playerId = "root";;

    @Before
    public void initPrefObject(){
        InitialProvider.init();
    }

    @Test
    public void playerIdTest(){
        NewPlayerData newPlayerData = new NewPlayerData();
        newPlayerData.setId(playerId);

        InitialProvider.provideInitialObject().saveIdPlayer(newPlayerData);
        assertTrue(playerId.equals(InitialProvider.provideInitialObject().getPlayerId()));
    }

    @Test
    public void getPlayerIdObjectTest(){
        PlayerId playerIdObject = new PlayerId(playerId);

        assertTrue(playerIdObject.equals(InitialProvider.provideInitialObject().getPlayerIdObject(playerId)));
    }

    @Test
    public void  getPlayerSettingsObjectTest(){
        String[] playerSettings = {"two", "root"};
        NewPlayerSettings newPlayerSettings = new NewPlayerSettings(playerSettings[0], playerSettings[1]);
        assertTrue(newPlayerSettings.equals(InitialProvider.provideInitialObject().getPlayerSettingsObject(playerSettings)));
    }
}
