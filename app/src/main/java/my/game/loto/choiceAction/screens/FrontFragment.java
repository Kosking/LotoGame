package my.game.loto.choiceAction.screens;


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.front_fragment, null);

        play_button = view.findViewById(R.id.play_button);
        play_button.setOnClickListener(this);

        setSettings();
        return view;
    }

    private void setSettings() {
    }

    @Override
    public void onClick(View v) {
        if(v.getId() != R.id.play_button) return;

        OnNextChoiceFragmentListener listener = (OnNextChoiceFragmentListener) getActivity();
        listener.onNextChoiceFragment();
    }

    public interface OnNextChoiceFragmentListener {
        void onNextChoiceFragment();
    }
}
