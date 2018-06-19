package frontScreen.presenter;

import com.example.work.loto.ChoicePresenter;
import com.example.work.loto.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import frontScreen.repository.MockConnectingRepository;
import ru.arturvasilov.rxloader.LifecycleHandler;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

import static junit.framework.TestCase.assertNotNull;

@RunWith(JUnit4.class)
public class ChoicePresenterTest {

        private MainActivity mainActivity;
        private ChoicePresenter choicePresenter;
        private MockConnectingRepository mockConnectingRepository;
        private LifecycleHandler mockLifecycleHandler;

        private String[] stringsPreferences;


        @Before
        public void setPresenter() throws Exception {
            mainActivity = Mockito.mock(MainActivity.class);
            mockConnectingRepository = new MockConnectingRepository();
            mockLifecycleHandler = new MockLifecycleHandler();
            choicePresenter = new ChoicePresenter(mainActivity, mockLifecycleHandler, mockConnectingRepository);
        }

        @Test
        public void testCreatedPresenter() throws Exception {
            assertNotNull(choicePresenter);
        }

        @Test
        public void testOpenChoiceFragment() throws Exception {
            mockSchedulers();
            stringsPreferences = new String[5];
            choicePresenter.setStringsPreferences(stringsPreferences);
            choicePresenter.onNextChoiceFragment();
            Mockito.verify(mainActivity).nextChoiceFragment(stringsPreferences);
        }

        private void mockSchedulers(){
            RxJavaHooks.setOnIOScheduler(scheduler -> Schedulers.immediate());
            RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
                @Override
                public Scheduler getMainThreadScheduler() {
                    return Schedulers.immediate();
                }
            });
        }
    }



