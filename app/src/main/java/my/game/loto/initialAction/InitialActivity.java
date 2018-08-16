package my.game.loto.initialAction;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import my.game.loto.R;
import my.game.loto.firstAction.screens.ChoiceActivity;
import my.game.loto.secondAction.screens.GameActivity;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class InitialActivity extends FragmentActivity implements InitialView {


    private FragmentManager fragmentManager;
    private FragmentTransaction fragTrans;
    private WelcomeFragment welcomeFragment;


    private InitialPresenter initialPresenter;
    private LifecycleHandler lifecycleHandler;

    private PrimaryData primaryData;
    private FullGameObject fullGameObject;

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
        Intent intent = new Intent(this, GameActivity.class);
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
}
