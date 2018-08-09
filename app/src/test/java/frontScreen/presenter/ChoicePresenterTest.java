package frontScreen.presenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import frontScreen.repository.MockConnectingRepository;
import frontScreen.repository.MockPreferenceObject;
import my.game.loto.firstAction.presenter.ChoicePresenter;
import my.game.loto.firstAction.repository.ConnectRepository;
import my.game.loto.firstAction.repository.Preferences;
import my.game.loto.firstAction.repository.RepositoryProvider;
import my.game.loto.firstAction.screens.ControlView;
import my.game.loto.firstAction.screens.StartGameActivity;
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
    public  final  RxJavaResetRule pluginsReset =  new  RxJavaResetRule ();

    @Before
    public void setPresenter() {
        //RxJavaPlugins.getInstance().reset();
        //RxAndroidPlugins.getInstance().reset();
        //mockSchedulers();
        startGameActivity = Mockito.mock(StartGameActivity.class);
        mockLifecycleHandler = new MockLifecycleHandler();
        choicePresenter = new ChoicePresenter(startGameActivity, mockLifecycleHandler);

    }

    @Test
    public void testCreatedPresenter() throws Exception {
        assertNotNull(choicePresenter);
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
        Mockito.verify(startGameActivity).nextToSecondActivity(null);

    }

    private String[] getDefaultPreferences() {
        String[] stringsReturned = new String[5];
        stringsReturned[0] = "slow";
        stringsReturned[1] = "short";
        stringsReturned[2] = "open";
        stringsReturned[3] = "two";
        stringsReturned[4] = "100";
        return stringsReturned;
    }

    private String[] getCustomPreferences() {
        String[] stringsReturned = new String[5];
        stringsReturned[0] = "normal";
        stringsReturned[1] = "long";
        stringsReturned[2] = "notOpen";
        stringsReturned[3] = "three";
        stringsReturned[4] = "200";
        return stringsReturned;
    }
}



