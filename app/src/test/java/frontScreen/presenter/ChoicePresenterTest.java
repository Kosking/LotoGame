package frontScreen.presenter;

import com.example.work.loto.ChoicePresenter;
import com.example.work.loto.MainActivity;

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
        private String[] stringsReturnedPreferences;

        @Test
        public void testCreatedPresenter() throws Exception {
            setPresenter();
            assertNotNull(choicePresenter);
        }

        @Test
        public void testOnNextChoiceFragment() throws Exception {
            setPresenter();
            mockSchedulers();
            stringsPreferences = new String[5];
            choicePresenter.setStringsPreferences(stringsPreferences);
            choicePresenter.onNextChoiceFragment();
            Mockito.verify(mainActivity).nextChoiceFragment(stringsPreferences);
        }

        @Test
            public void testSetStringsPreferences() throws Exception {
            setPresenterForStringsPreferences();
            mockSchedulers();
            stringsReturnedPreferences = getReturnedPreferences();
            choicePresenter.onNextWaitFragment(stringsReturnedPreferences);
            Mockito.verify(mockConnectingRepository).setPreferences(stringsReturnedPreferences);
        }

        private void setPresenter() throws Exception {
            mainActivity = Mockito.mock(MainActivity.class);
            mockConnectingRepository = new MockConnectingRepository();
            mockLifecycleHandler = new MockLifecycleHandler();
            choicePresenter = new ChoicePresenter(mainActivity, mockLifecycleHandler, mockConnectingRepository);
        }

        private void setPresenterForStringsPreferences() throws Exception {
            mainActivity = Mockito.mock(MainActivity.class);
            mockConnectingRepository = Mockito.mock(MockConnectingRepository.class);
            mockLifecycleHandler = new MockLifecycleHandler();
            choicePresenter = new ChoicePresenter(mainActivity, mockLifecycleHandler, mockConnectingRepository);
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

        private String[] getReturnedPreferences() {
            String[] stringsReturned = new String[5];
            stringsReturned[0] = "normal";
            stringsReturned[1] = "long";
            stringsReturned[2] = "notOpen";
            stringsReturned[3] = "three";
            stringsReturned[4] = "200";
            return stringsReturned;
        }
    }



