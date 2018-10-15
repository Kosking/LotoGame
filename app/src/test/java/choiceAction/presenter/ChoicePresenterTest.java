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
import my.game.loto.choiceAction.repository.ChoicePreference;
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
    private ChoicePreference mockPreferenceObject;
    private LifecycleHandler mockLifecycleHandler;
    private ConnectRepository mockConnectRepository;


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
    public void startDataTest(){
        mockPreferenceObject = Mockito.spy(new MockPreferenceObject());
        RepositoryProvider.setPreferenceObject(mockPreferenceObject);

        choicePresenter.startData();
        Mockito.verify(startGameActivity).setFragment(null);
    }

    @Test
    public void onNextChoiceFragmentTest() throws Exception {
        mockPreferenceObject = new MockPreferenceObject();
        RepositoryProvider.setPreferenceObject(mockPreferenceObject);

        choicePresenter.onNextChoiceFragment();
        Mockito.verify(startGameActivity).nextChoiceFragment(null);
    }

    @Test
    public void onNextWaitFragmentTest() throws Exception {
        mockConnectRepository = new MockConnectingRepository();
        mockPreferenceObject = Mockito.spy(new MockPreferenceObject());

        RepositoryProvider.setConnectingRepository(mockConnectRepository);
        RepositoryProvider.setPreferenceObject(mockPreferenceObject);

        choicePresenter.onNextWaitFragment(null);
        Mockito.verify(mockPreferenceObject).setPreferences(null);
        Mockito.verify(startGameActivity).nextWaitFragment();
        Mockito.verify(mockPreferenceObject).setListPlayObjects(null);
        Mockito.verify(startGameActivity).nextSecondAction();
    }
}



