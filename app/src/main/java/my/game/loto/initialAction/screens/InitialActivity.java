package my.game.loto.initialAction.screens;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import my.game.loto.R;
import my.game.loto.choiceAction.screens.ChoiceActivity;
import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;
import my.game.loto.initialAction.presenter.InitialPresenter;
import my.game.loto.initialAction.repository.InitialProvider;
import my.game.loto.gameAction.screens.GameActivityKt;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class InitialActivity extends FragmentActivity implements WelcomeFragment.NextFreshChoiceActivityListener, InitialView {


    private FragmentManager fragmentManager;
    private FragmentTransaction fragTrans;
    private WelcomeFragment welcomeFragment;


    private InitialPresenter initialPresenter;
    private LifecycleHandler lifecycleHandler;

    private PrimaryData primaryData;
    private FullGameObject fullGameObject;
    private NewPlayerData newPlayerData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_activity);

        lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        initialPresenter = new InitialPresenter(this, lifecycleHandler);
        InitialProvider.init();

        initialPresenter.upAction();
    }

    @Override
    public void nextWelcomeFragment() {
        fragmentManager = getFragmentManager();
        fragTrans = fragmentManager.beginTransaction();
        welcomeFragment = new WelcomeFragment();

        //fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

        fragTrans.replace(R.id.container_for_frag, welcomeFragment);
        fragTrans.addToBackStack(null);
        fragTrans.commit();
    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void nextGameActivity(FullGameObject fullGameObject) {
        this.fullGameObject = fullGameObject;
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("FullGameObject.out"));){
            output.writeObject(this.fullGameObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, GameActivityKt.class);
        startActivity(intent);
    }

    @Override
    public void nextChoiceActivity(PrimaryData primaryData) {
        this.primaryData = primaryData;
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("PrimaryData.out"));){
            output.writeObject(this.primaryData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, ChoiceActivity.class);
        startActivity(intent);
    }

    @Override
    public void nextFreshChoiceActivity(String[] playerSettings) {
        initialPresenter.uploadingNewPlayerId(playerSettings);
    }

    @Override
    public void freshChoiceActivity(NewPlayerData newPlayerData) {
        this.newPlayerData = newPlayerData;
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("NewPlayerData.out"));){
            output.writeObject(this.newPlayerData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, ChoiceActivity.class);
        startActivity(intent);
    }
}
