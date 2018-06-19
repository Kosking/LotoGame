package repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.work.loto.ConnectingRepository;
import com.example.work.loto.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.test.MoreAsserts.assertEquals;


@RunWith(AndroidJUnit4.class)
public class ConnectingRepositoryTest {

    private SharedPreferences sharedPreferences;
    private ConnectingRepository connectingRepository;
    private Context context;
    private String[] stringsPreferences;

    @Rule
    public final ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setPresenter() throws Exception {
        context = getInstrumentation().getTargetContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        connectingRepository = new ConnectingRepository(sharedPreferences);
    }

    @Test
    public void testSharedPreferences() throws Exception {
        stringsPreferences = new String[5];
        stringsPreferences[0] = "fast";
        stringsPreferences[1] = "short";
        stringsPreferences[2] = "close";
        stringsPreferences[3] = "four";
        stringsPreferences[4] = "200";
        connectingRepository.setPreferences(stringsPreferences);
        connectingRepository
                .getPreferences()
                .subscribe(preferencesObject ->
                        assertEquals(preferencesObject, stringsPreferences));
    }
}
