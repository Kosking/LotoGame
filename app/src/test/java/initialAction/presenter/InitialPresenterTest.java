package initialAction.presenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import forTests.MockLifecycleHandler;
import forTests.RxJavaResetRule;
import initialAction.repository.MockInitialObject;
import initialAction.repository.MockPreparatoryRepository;
import my.game.loto.initialAction.presenter.InitialPresenter;
import my.game.loto.initialAction.repository.InitialPreference;
import my.game.loto.initialAction.repository.InitialProvider;
import my.game.loto.initialAction.repository.PrepareRepository;
import my.game.loto.initialAction.screens.InitialActivity;
import my.game.loto.initialAction.screens.InitialView;
import ru.arturvasilov.rxloader.LifecycleHandler;

import static junit.framework.Assert.assertNotNull;

@Config(sdk = 27, manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class InitialPresenterTest {

    private InitialView initialActivity;
    private InitialPresenter initialPresenter;

    private InitialPreference initialObject;
    private MockInitialObject mockInitialObject;
    private PrepareRepository preparatoryRepository;
    private MockPreparatoryRepository mockPreparatoryRepository;

    private static final String nullId = "";
    private static final String nonNullId = "nonNullId";
    private static final String fullPlayerToken = "true";
    private static final String nullPlayerToken = "null";

    @Rule
    public final RxJavaResetRule pluginsReset =  new  RxJavaResetRule();

    @Before
    public void setPresenter() {
        initialActivity = Mockito.mock(InitialActivity.class);
        LifecycleHandler mockLifecycleHandler = new MockLifecycleHandler();
        initialPresenter = new InitialPresenter(initialActivity, mockLifecycleHandler);

        initialObject = Mockito.spy(new MockInitialObject());
        InitialProvider.setPreferenceObject(initialObject);
        mockInitialObject = (MockInitialObject) initialObject;
    }

    @Test
    public void createPresenterTest() throws Exception {
        assertNotNull(initialPresenter);
    }

    @Test
    public void upActionNullIdTest(){
        mockInitialObject.setPlayerId(nullId);
        initialPresenter.upAction();

        Mockito.verify(initialObject).getPlayerId();
        Mockito.verify(initialActivity).nextWelcomeFragment();
    }

    @Test
    public void upActionWithPlayerTokenTest(){
        mockInitialObject.setPlayerId(nonNullId);
        setPreparatoryRepository();

        mockPreparatoryRepository.setPlayerToken(fullPlayerToken);
        initialPresenter.upAction();

        Mockito.verify(initialObject).getPlayerId();
        Mockito.verify(preparatoryRepository).setPlayerIdObject(nonNullId);
        Mockito.verify(preparatoryRepository).getPlayerGameToken();
        Mockito.verify(preparatoryRepository).getPlayData();
        Mockito.verify(initialObject).setFullGameObject(null);
        Mockito.verify(initialActivity).nextGameActivity();
    }

    @Test
    public void upActionWithoutPlayerTokenTest(){
        mockInitialObject.setPlayerId(nonNullId);
        setPreparatoryRepository();

        mockPreparatoryRepository.setPlayerToken(nullPlayerToken);
        initialPresenter.upAction();

        Mockito.verify(initialObject).getPlayerId();
        Mockito.verify(preparatoryRepository).setPlayerIdObject(nonNullId);
        Mockito.verify(preparatoryRepository).getPlayerGameToken();
        Mockito.verify(preparatoryRepository).getPrimaryData();
        Mockito.verify(initialObject).setPrimaryData(null);
        Mockito.verify(initialActivity).nextChoiceActivity();
    }

    @Test
    public void downloadingNewPlayerIdTest(){
        setPreparatoryRepository();
        initialPresenter.downloadingNewPlayerId(null);

        Mockito.verify(initialObject).setNamePlayer(null);
        Mockito.verify(preparatoryRepository).createNewPlayer(null);
        Mockito.verify(initialObject).setNewPlayerData(null);
        Mockito.verify(initialActivity).nextChoiceActivity();
    }

    private void setPreparatoryRepository() {
        preparatoryRepository = Mockito.spy(new MockPreparatoryRepository());
        InitialProvider.setPreparatoryRepository(preparatoryRepository);
        mockPreparatoryRepository = (MockPreparatoryRepository) preparatoryRepository;
    }
}
