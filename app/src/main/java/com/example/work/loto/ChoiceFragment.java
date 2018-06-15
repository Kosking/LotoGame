package com.example.work.loto;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.ToggleButton;

public class ChoiceFragment extends Fragment implements View.OnClickListener {

    Button playButton;
    RadioButton slowSpeedButton;
    RadioButton normalSpeedButton;
    RadioButton fastSpeedButton;
    CheckBox boxModeCards;
    ToggleButton modeRoomButton;
    CheckBox boxTwoPlayers;
    CheckBox boxThreePlayers;
    CheckBox boxFourPlayers;
    CheckBox boxFivePlayers;

    CheckBox box200;
    CheckBox box100;

    private String[] settingsStrings;
    private static final String stringsPreferences = "stringsPreferences";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choice_fragment, null);

        playButton = (Button) view.findViewById(R.id.play_button_rand);
        playButton.setOnClickListener(this);

        slowSpeedButton = (RadioButton) view.findViewById(R.id.slow_speed);
        normalSpeedButton = (RadioButton) view.findViewById(R.id.normal_speed);
        fastSpeedButton = (RadioButton) view.findViewById(R.id.fast_speed);
        boxModeCards = (CheckBox) view.findViewById(R.id.mode_cards_checkBox);
        modeRoomButton = (ToggleButton) view.findViewById(R.id.button_mode_room);

        boxTwoPlayers = (CheckBox) view.findViewById(R.id.box_two_players);
        boxThreePlayers = (CheckBox) view.findViewById(R.id.box_three_players);
        boxFourPlayers = (CheckBox) view.findViewById(R.id.box_four_players);
        boxFivePlayers = (CheckBox) view.findViewById(R.id.box_five_players);

        box200 = (CheckBox) view.findViewById(R.id.box200);
        box200 = (CheckBox) view.findViewById(R.id.box100);

        setSettings();
        return view;
    }

    private void setSettings() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            settingsStrings = bundle.getStringArray(stringsPreferences);
            if (settingsStrings != null){
                setSpeed(settingsStrings);
                setModeCards(settingsStrings);
                setModeRoom(settingsStrings);
                setQuantityPlayers(settingsStrings);
                setRate(settingsStrings);
            }
        }
    }

    private void setSpeed(String[] preferences) {
        if (preferences[0] == "")
            slowSpeedButton.setChecked(true);
        if (preferences[0] == "normal")
            slowSpeedButton.setChecked(true);
        if (preferences[0] == "fast")
            slowSpeedButton.setChecked(true);
    }

    private void setModeCards(String[] modeCards) {
        if (modeCards[1] == "long")
            boxModeCards.setChecked(true);
    }

    private void setModeRoom(String[] modeRoom) {
        if(modeRoom[2] == "notOpen"){
            modeRoomButton.setChecked(true);
        }
    }

    private void setQuantityPlayers(String[] quantityPlayers) {
        if (quantityPlayers[3] == ""){
            boxTwoPlayers.setChecked(true);
        }
        if (quantityPlayers[3] == "three"){
            boxThreePlayers.setChecked(true);
        }
        if (quantityPlayers[3] == "four"){
            boxFourPlayers.setChecked(true);
        }
        if (quantityPlayers[3] == "five"){
            boxFivePlayers.setChecked(true);
        }
    }
     //TODO rate should is retrofit field with custom slider
    private void setRate(String[] rate) {
        if (rate[4] != "100"){
            box200.setChecked(true);
        }
        else {
            box100.setChecked(true);
        }
    }


    @Override
    public void onClick(View v) {

        if(v.getId() != R.id.play_button_rand) return;
        OnNextWaitFragmentListener listener = (OnNextWaitFragmentListener) getActivity();
        listener.OnNextWaitFragment();

    }

    interface OnNextWaitFragmentListener{
        void OnNextWaitFragment();
    }

}