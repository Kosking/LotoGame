package repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.runner.RunWith;

import my.game.loto.firstAction.repository.ConnectingRepository;
import my.game.loto.firstAction.screens.StartGameActivity;


@RunWith(AndroidJUnit4.class)
public class ConnectingRepositoryTest {

    private SharedPreferences sharedPreferences;
    private ConnectingRepository connectingRepository;
    private Context context;
    private String[] stringsPreferences;

    @Rule
    public final ActivityTestRule<StartGameActivity> mainActivityRule = new ActivityTestRule<>(StartGameActivity.class);

    /*@Before
    public void setPresenter() {

        context = getInstrumentation().getTargetContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        connectingRepository = new ConnectingRepository(sharedPreferences);
    }*/


}
