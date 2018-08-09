package screens;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import my.game.loto.R;
import my.game.loto.firstAction.screens.StartGameActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class WaitFragmentTest {

    @Rule
    public final ActivityTestRule<StartGameActivity> mainActivityRule = new ActivityTestRule<>(StartGameActivity.class);

    @Test
    public void testModeRoomButton() throws Exception {
        onView(withId(R.id.play_button)).perform(click());
        onView(withId(R.id.play_button_rand)).perform(click());
        onView(withId(R.id.go_button)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isClickable(),
                withText("Button")
        )));
    }

}
