package com.example.work.loto.FirstAction.Screens;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.work.loto.FirstAction.Presenter.ChoicePresenter;
import com.example.work.loto.FirstAction.Repository.ConnectingRepository;
import com.example.work.loto.FirstAction.Repository.Retrofit.SettingsObjects.PlayObject;
import com.example.work.loto.R;
import com.example.work.loto.SecondAction.GameActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class StartGameActivity extends FragmentActivity implements FrontFragment.OnNextChoiceFragmentListener,
                                                        ChoiceFragment.OnNextWaitFragmentListener, ControlView {


    private FragmentManager fragmentManager;
    private ChoiceFragment choiceFragment;
    private WaitFragment waitFragment;
    private FragmentTransaction fragTrans;
    private ChoicePresenter choicePresenter;
    private SharedPreferences sharedPreferences;
    private ConnectingRepository defaultRepository;
    private LifecycleHandler lifecycleHandler;

    private final String stringsPreferences = "stringsPreferences";
    private String[] settings;
    private String[] settingsStrings;

    List<PlayObject> playObject;

    //TODO choicePresenter.init() in StartActivity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        defaultRepository = new ConnectingRepository(sharedPreferences);
        lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        choicePresenter = new ChoicePresenter(this, lifecycleHandler, defaultRepository );

        //choicePresenter.init();
    }


    @Override
    public void OnNextChoiceFragment() {
        choicePresenter.onNextChoiceFragment();
    }

    @Override
    public void OnNextWaitFragment(String[] settingsStrings) {
        settings = settingsStrings;
        choicePresenter.onNextWaitFragment(settings);
    }

    //TODO Transaction fragment
    @Override
    public void nextChoiceFragment(String[] preferences) {
        settingsStrings = preferences;
        fragmentManager = getFragmentManager();
        fragTrans = fragmentManager.beginTransaction();
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
    public void nextWaitFragment(){
        changeToWaitFragment();
    }

    //TODO Transaction fragment
    private void changeToWaitFragment(){
        fragmentManager = getFragmentManager();
        fragTrans = fragmentManager.beginTransaction();
        waitFragment = new WaitFragment();

        //fragTrans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

        fragTrans.replace(R.id.container_for_frag,waitFragment);
        fragTrans.addToBackStack(null);
        fragTrans.commit();

    }

    //TODO openPlayingScreen in fragment
    public void openPlayingScreen() {
    }

    @Override
    //TODO showLoginError in fragment
    public void showLoadingError() {
    }

    @Override
    public void nextToSecondActivity(List<PlayObject> playObject) throws IOException {
        this.playObject = playObject;
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("startObjects.out"));
        output.writeObject(this.playObject);
        output.close();
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}