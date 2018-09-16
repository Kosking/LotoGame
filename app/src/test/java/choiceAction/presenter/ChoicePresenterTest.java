package choiceAction.presenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import choiceAction.repository.MockConnectingRepository;
import choiceAction.repository.MockPreferenceObject;
import forTests.MockLifecycleHandler;
import forTests.RxJavaResetRule;
import my.game.loto.choiceAction.presenter.ChoicePresenter;
import my.game.loto.choiceAction.repository.ConnectRepository;
import my.game.loto.choiceAction.repository.Preferences;
import my.game.loto.choiceAction.repository.RepositoryProvider;
import my.game.loto.choiceAction.screens.ChoiceActivity;
import my.game.loto.choiceAction.screens.ControlView;
import ru.arturvasilov.rxloader.LifecycleHandler;

import static junit.framework.TestCase.assertNotNull;

@Config(sdk = 27, manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class ChoicePresenterTest {

    private ControlView startGameActivity;
    private ChoicePresenter choicePresenter;
    private Preferences mockPreferenceObject;
    private LifecycleHandler mockLifecycleHandler;
    private ConnectRepository mockConnectRepository;

    private String[] stringsDefaultPreferences;
    private String[] stringsCustomPreferences;


    @Rule
    public  final RxJavaResetRule pluginsReset =  new  RxJavaResetRule ();

    @Before
    public void setPresenter() {
        startGameActivity = Mockito.mock(ChoiceActivity.class);
        mockLifecycleHandler = new MockLifecycleHandler();
        choicePresenter = new ChoicePresenter(startGameActivity, mockLifecycleHandler);
    }

    @Test
    public void testCreatedPresenter() throws Exception {
        assertNotNull(choicePresenter);
    }

    @Test
    public void testGetPlayerName(){
        mockPreferenceObject = new MockPreferenceObject();
        RepositoryProvider.setPreferenceObject(mockPreferenceObject);

        choicePresenter.getPlayerName();
        Mockito.verify(startGameActivity).setPlayerName(null);
    }

    @Test
    public void testOnNextChoiceFragment() throws Exception {
        stringsDefaultPreferences = getDefaultPreferences();
        mockPreferenceObject = new MockPreferenceObject();
        RepositoryProvider.setPreferenceObject(mockPreferenceObject);
        //choicePresenter.setStringsPreferences(stringsDefaultPreferences);
        choicePresenter.onNextChoiceFragment();
        Mockito.verify(startGameActivity).nextChoiceFragment(stringsDefaultPreferences);
    }

    @Test
    public void testOnNextWaitFragment() throws Exception {
        stringsCustomPreferences = getCustomPreferences();
        mockConnectRepository = new MockConnectingRepository();
        mockPreferenceObject = Mockito.spy(new MockPreferenceObject());

        RepositoryProvider.setConnectingRepository(mockConnectRepository);
        RepositoryProvider.setPreferenceObject(mockPreferenceObject);

        choicePresenter.onNextWaitFragment(stringsCustomPreferences);
        Mockito.verify(mockPreferenceObject).setPreferences(stringsCustomPreferences);
        Mockito.verify(startGameActivity).nextWaitFragment();
        Mockito.verify(startGameActivity).nextSecondActivity(null);

    }

    private String[] getDefaultPreferences() {
        String[] stringsReturned = new String[6];
        stringsReturned[0] = "slow";
        stringsReturned[1] = "short";
        stringsReturned[2] = "open";
        stringsReturned[3] = "two";
        stringsReturned[4] = "100";
        stringsReturned[5] = "root";
        return stringsReturned;
    }

    private String[] getCustomPreferences() {
        String[] stringsReturned = new String[6];
        stringsReturned[0] = "normal";
        stringsReturned[1] = "long";
        stringsReturned[2] = "notOpen";
        stringsReturned[3] = "three";
        stringsReturned[4] = "200";
        stringsReturned[5] = "root2";
        return stringsReturned;
    }
}



