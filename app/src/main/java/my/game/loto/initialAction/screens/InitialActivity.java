package my.game.loto.initialAction.screens;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import my.game.loto.R;
import my.game.loto.choiceAction.screens.ChoiceActivity;
import my.game.loto.gameAction.screens.GameActivity;
import my.game.loto.initialAction.presenter.InitialPresenter;
import my.game.loto.initialAction.repository.InitialProvider;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class InitialActivity extends FragmentActivity implements WelcomeFragment.NextFreshChoiceActivityListener, InitialView {


    private InitialPresenter initialPresenter;
    private LifecycleHandler lifecycleHandler;


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
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragTrans = fragmentManager.beginTransaction();
        WelcomeFragment welcomeFragment = new WelcomeFragment();

        //fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

        fragTrans.replace(R.id.container_for_frag, welcomeFragment);
        fragTrans.addToBackStack(null);
        fragTrans.commit();
    }

    @Override
    public void showLoadingError() {

    }

    @Override
    public void nextGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    @Override
    public void nextChoiceActivity() {
        Intent intent = new Intent(this, ChoiceActivity.class);
        startActivity(intent);
    }

    @Override
    public void nextFreshChoiceActivity(String[] playerSettings) {
        initialPresenter.downloadingNewPlayerId(playerSettings);
    }
}
