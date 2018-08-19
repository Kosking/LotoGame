package my.game.loto.firstAction.screens;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import my.game.loto.R;


public class FrontFragment extends Fragment implements View.OnClickListener
{
    private Button play_button;
    private String playerName;
    private static final String keyPlayerName = "myPlayerName";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.front_fragment, null);

        play_button = (Button) view.findViewById(R.id.play_button);
        play_button.setOnClickListener(this);

        setSettings();
        return view;
    }

    private void setSettings() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            playerName = bundle.getString(keyPlayerName);
            if (playerName != null){
                setPlayerName(playerName);
            }
        }
    }

    @Override
    public void onClick(View v) {

        if(v.getId() != R.id.play_button) return;

        OnNextChoiceFragmentListener listener = (OnNextChoiceFragmentListener) getActivity();
        listener.onNextChoiceFragment();

    }
    //TODO setPlayerName
    public void setPlayerName(String playerName) {
    }

    public interface OnNextChoiceFragmentListener {
        void onNextChoiceFragment();
    }

}
