package choiceAction.presenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import choiceAction.repository.MockConnectingRepository;
import choiceAction.repository.MockChoiceObject;
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
    private ChoicePreference choiceObject;
    private LifecycleHandler mockLifecycleHandler;
    private ConnectRepository mockConnectRepository;

    @Rule
    public final RxJavaResetRule pluginsReset =  new  RxJavaResetRule();

    @Before
    public void setPresenter() {
        startGameActivity = Mockito.mock(ChoiceActivity.class);
        mockLifecycleHandler = new MockLifecycleHandler();
        choicePresenter = new ChoicePresenter(startGameActivity, mockLifecycleHandler);

        choiceObject = Mockito.spy(new MockChoiceObject());
        RepositoryProvider.setChoiceObject(choiceObject);
    }

    @Test
    public void testCreatedPresenter() throws Exception {
        assertNotNull(choicePresenter);
    }

    @Test
    public void startDataTest(){
        choicePresenter.startData();
        Mockito.verify(choiceObject).getStartObject();
        Mockito.verify(startGameActivity).setFragment(null);
    }

    @Test
    public void onNextChoiceFragmentTest() throws Exception {
        choicePresenter.onNextChoiceFragment();
        Mockito.verify(choiceObject).getPreferences();
        Mockito.verify(startGameActivity).nextChoiceFragment(null);
    }

    //@Test
    public void onNextWaitFragmentTest() throws Exception {
        mockConnectRepository = new MockConnectingRepository();
        RepositoryProvider.setConnectingRepository(mockConnectRepository);

        choicePresenter.onNextWaitFragment(null);
        Mockito.verify(choiceObject).setPreferences(null);
        Mockito.verify(startGameActivity).nextWaitFragment();
        Mockito.verify(choiceObject).setListPlayObjects(null);
        Mockito.verify(startGameActivity).nextSecondAction();
    }
}



