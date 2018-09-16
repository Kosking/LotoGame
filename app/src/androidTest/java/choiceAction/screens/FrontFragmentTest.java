package choiceAction.screens;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import my.game.loto.R;
import my.game.loto.choiceAction.screens.ChoiceActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class FrontFragmentTest {

    @Rule
    public final ActivityTestRule<ChoiceActivity> mainActivityRule = new ActivityTestRule<>(ChoiceActivity.class);


    @Test
    public void testStartButton() throws Exception {
        onView(ViewMatchers.withId(R.id.play_button)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isClickable(),
                withText(R.string.play_button)
        )));
    }
    @Test
    public void testSettingsButton() throws Exception {
        onView(withId(R.id.settings_button)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isClickable(),
                withText(R.string.settings_button)
        )));
    }

    @Test
    public void testHelpButton() throws Exception {
        onView(withId(R.id.help_button)).check(matches(allOf(
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                isClickable(),
                withText(R.string.help_button)
        )));
    }
}
