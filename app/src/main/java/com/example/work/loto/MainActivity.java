package com.example.work.loto;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class MainActivity extends FragmentActivity implements FrontFragment.OnNextChoiceFragmentListener,
                                                        ChoiceFragment.OnNextWaitFragmentListener,
                                                        ControlView{

    private FragmentManager fragmentManager;
    private ChoiceFragment choiceFragment;
    private WaitFragment waitFragment;
    private FragmentTransaction fragTrans;
    private ChoicePresenter choicePresenter;


    private final String stringsPreferences = "stringsPreferences";

    //TODO choicePresenter.init() in StartActivity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        choicePresenter = new ChoicePresenter(this, lifecycleHandler);
        choicePresenter.settingsInit();
        //choicePresenter.init();
    }


    @Override
    public void OnNextChoiceFragment() {
        choicePresenter.onNextChoiceFragment();
    }

    @Override
    public void OnNextWaitFragment() {
        choicePresenter.onNextWaitFragment();
    }

    //TODO Transaction fragment
    @Override
    public void nextChoiceFragment(String[] preferences) {
        fragmentManager = getFragmentManager();
        fragTrans = fragmentManager.beginTransaction();
        choiceFragment = new ChoiceFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(stringsPreferences, preferences);
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
}