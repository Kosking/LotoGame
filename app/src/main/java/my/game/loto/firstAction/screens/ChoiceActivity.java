package my.game.loto.firstAction.screens;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import my.game.loto.R;
import my.game.loto.firstAction.presenter.ChoicePresenter;
import my.game.loto.firstAction.repository.RepositoryProvider;
import my.game.loto.firstAction.retrofit.settingsObjects.PlayObject;
import my.game.loto.secondAction.screens.GameActivity;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class ChoiceActivity extends FragmentActivity implements FrontFragment.OnNextChoiceFragmentListener,
                                                        ChoiceFragment.OnNextWaitFragmentListener, ControlView {


    private FragmentManager fragmentManager;
    private ChoiceFragment choiceFragment;
    private WaitFragment waitFragment;
    private FrontFragment frontFragment;
    private FragmentTransaction fragTrans;
    private ChoicePresenter choicePresenter;
    private LifecycleHandler lifecycleHandler;

    private static final String stringsPreferences = "stringsPreferences";
    private String[] settings;
    private String[] settingsStrings;
    private String playerName;
    private static final String keyPlayerName = "myPlayerName";

    private List<PlayObject> playObject;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_activity);

        lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        choicePresenter = new ChoicePresenter(this, lifecycleHandler);
        RepositoryProvider.init();

        choicePresenter.getPlayerName();
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        Bundle bundle = new Bundle();
        bundle.putString(keyPlayerName, this.playerName);
        frontFragment = new FrontFragment();
        frontFragment.setArguments(bundle);

        fragTrans = getFragmentManager().beginTransaction();
        fragTrans.add(R.id.container_for_frag, frontFragment);
        fragTrans.commit();
    }

    @Override
    public void onNextChoiceFragment() {
        choicePresenter.onNextChoiceFragment();
    }

    //TODO Transaction fragment
    @Override
    public void nextChoiceFragment(String[] preferences) {
        settingsStrings = preferences;
        fragTrans = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putStringArray(stringsPreferences, settingsStrings);
        choiceFragment = new ChoiceFragment();
        choiceFragment.setArguments(bundle);

        //fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

        fragTrans.replace(R.id.container_for_frag, choiceFragment);
        fragTrans.addToBackStack(null);
        fragTrans.commit();

    }

    @Override
    public void onNextWaitFragment(String[] settingsStrings) {
        settings = settingsStrings;
        choicePresenter.onNextWaitFragment(settings);
    }

    @Override
    public void nextWaitFragment(){
        changeToWaitFragment();
    }

    //TODO Transaction fragment
    private void changeToWaitFragment(){
        fragTrans = getFragmentManager().beginTransaction();
        waitFragment = new WaitFragment();

        //fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

        fragTrans.replace(R.id.container_for_frag,waitFragment);
        fragTrans.addToBackStack(null);
        fragTrans.commit();

    }

    @Override
    //TODO showLoginError in fragment
    public void showLoadingError() {
    }

    @Override
    public void nextSecondActivity(List<PlayObject> playObject){
        this.playObject = playObject;
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("StartObjects.out"));){
            output.writeObject(this.playObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}