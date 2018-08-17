package my.game.loto.initialAction.screens;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import my.game.loto.R;

public class WelcomeFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_fragment, null);


        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_new_account){

            NextFreshChoiceActivityListener listener = (NextFreshChoiceActivityListener) getActivity();
            listener.nextFreshChoiceActivity(getPlayerSettings());
        }
    }

    public String[] getPlayerSettings() {
        String[] playerSettings = new String[2];
        playerSettings[0] = "myImage";
        playerSettings[1] = "root";
        return playerSettings;
    }

    interface NextFreshChoiceActivityListener{
        void nextFreshChoiceActivity(String[] playerSettings);
    }
}
