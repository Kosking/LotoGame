package com.example.work.loto.firstScreen;

import com.example.work.loto.ChoicePresenter;
import com.example.work.loto.MainActivity;
import com.orhanobut.hawk.Hawk;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import ru.arturvasilov.rxloader.LifecycleHandler;

import static junit.framework.TestCase.assertNotNull;

    @RunWith(JUnit4.class)
public class ChoicePresenterTest {

        private MainActivity mainActivity;
        private LifecycleHandler lifecycleHandler;
        private ChoicePresenter choicePresenter;
        String[] string;

        @Before
        public void setPresenter() throws Exception {
            mainActivity = Mockito.mock(MainActivity.class);
            lifecycleHandler = Mockito.mock(LifecycleHandler.class);
            choicePresenter = new ChoicePresenter(mainActivity, lifecycleHandler);
            choicePresenter.settingsInit();
        }

        @Test
        public void testCreated() throws Exception {
            assertNotNull(choicePresenter);
        }

        @Test
        public void testOpenChoiceFragment() throws Exception {
            choicePresenter.onNextChoiceFragment();
            Mockito.verify(mainActivity).nextChoiceFragment(string);
        }

        @Test
        public void testCreatedHawk() throws Exception {
            settingsInit();
            //Mockito.verify(Hawk).init(mainActivity).build();
        }

        private void settingsInit(){
            Hawk.init(mainActivity).build();
        }
    }



