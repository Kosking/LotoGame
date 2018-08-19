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
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.screens.InitialActivity;
import my.game.loto.initialAction.screens.InitialView;
import ru.arturvasilov.rxloader.LifecycleHandler;

import static junit.framework.Assert.assertNotNull;

@Config(sdk = 27, manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class InitialPresenterTest {

    private InitialView initialActivity;
    private LifecycleHandler mockLifecycleHandler;
    private InitialPresenter initialPresenter;

    private InitialPreference initialObject;
    private PrepareRepository preparatoryRepository;

    private static final String nullId = "";
    private static final String nonNullId = "nonNullId";
    private static final String playerToken = "true";
    private static final String nullPlayerToken = "null";
    private NewPlayerData newPlayerData;

    @Rule
    public  final RxJavaResetRule pluginsReset =  new  RxJavaResetRule ();

    @Before
    public void setPresenter() {
        initialActivity = Mockito.mock(InitialActivity.class);
        mockLifecycleHandler = new MockLifecycleHandler();
        initialPresenter = new InitialPresenter(initialActivity, mockLifecycleHandler);

        initialObject = Mockito.spy(new MockInitialObject());
        InitialProvider.setPreferenceObject(initialObject);
    }

    @Test
    public void testCreatedPresenter() throws Exception {
        assertNotNull(initialPresenter);
    }

    @Test
    public void testUpActionNullId(){
        setNewPlayerData(nullId);
        InitialProvider.provideInitialObject().saveIdPlayer(newPlayerData);
        initialPresenter.upAction();

        Mockito.verify(initialActivity).nextWelcomeFragment();
    }

    @Test
    public void testUpActionWithPlayerToken(){
        setNewPlayerData(nonNullId);
        setPreparatoryRepository();

        InitialProvider.provideInitialObject().saveIdPlayer(newPlayerData);
        InitialProvider.providePreparatoryRepository().setPlayerToken(playerToken);
        initialPresenter.upAction();

        Mockito.verify(preparatoryRepository).setPlayerIdObject(nonNullId);
        Mockito.verify(initialActivity).nextGameActivity(null);
    }

    @Test
    public void testUpActionWithoutPlayerToken(){
        setNewPlayerData(nonNullId);
        setPreparatoryRepository();

        InitialProvider.provideInitialObject().saveIdPlayer(newPlayerData);
        InitialProvider.providePreparatoryRepository().setPlayerToken(nullPlayerToken);
        initialPresenter.upAction();

        Mockito.verify(preparatoryRepository).setPlayerIdObject(nonNullId);
        Mockito.verify(initialActivity).nextChoiceActivity(null);
    }

    @Test
    public void testUploadingNewPlayerId(){
        setPreparatoryRepository();

        initialPresenter.uploadingNewPlayerId(null);

        Mockito.verify(initialObject).saveNamePlayer(null);
        Mockito.verify(initialObject).saveIdPlayer(null);
        Mockito.verify(initialActivity).freshChoiceActivity(null);
    }

    private void setNewPlayerData(String myId){
        newPlayerData = new NewPlayerData();
        newPlayerData.setId(myId);
    }

    private void setPreparatoryRepository() {
        preparatoryRepository = Mockito.spy(new MockPreparatoryRepository());
        InitialProvider.setPreparatoryRepository(preparatoryRepository);
    }
}
