package com.example.work.loto.FirstAction.Screens;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.example.work.loto.R;

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

    RadioGroup playersRadioGroup;
    RadioGroup rateRadioGroup;

    private String[] setSettingsStrings;
    private String[] getSettingsStrings;
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
        box100 = (CheckBox) view.findViewById(R.id.box100);

        playersRadioGroup = view.findViewById(R.id.players_radio_group);
        rateRadioGroup = view.findViewById(R.id.rate_radio_group);

        setSettings();
        return view;
    }

    private void setSettings() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            setSettingsStrings = bundle.getStringArray(stringsPreferences);
            if (setSettingsStrings != null){
                setSpeed(setSettingsStrings[0]);
                setModeCards(setSettingsStrings[1]);
                setModeRoom(setSettingsStrings[2]);
                setQuantityPlayers(setSettingsStrings[3]);
                setRate(setSettingsStrings[4]);
            }
        }
    }

    private void setSpeed(String preferences) {
        if (preferences.equals("slow")){
            slowSpeedButton.setChecked(true);
        }
        else if (preferences.equals("normal")){
            normalSpeedButton.setChecked(true);
        }
        else if (preferences.equals("fast")){
            fastSpeedButton.setChecked(true);
        }
    }

    private void setModeCards(String modeCards) {
        if (modeCards.equals("short")) {
            boxModeCards.setChecked(true);
        }
        else {
            boxModeCards.setChecked(false);
        }
    }

    private void setModeRoom(String modeRoom) {
        if(modeRoom.equals("open")){
            modeRoomButton.setChecked(true);
        }
        else {
            modeRoomButton.setChecked(false);
        }
    }

    private void setQuantityPlayers(String quantityPlayers) {
        if (quantityPlayers.equals("two")){
            boxTwoPlayers.setChecked(true);
        }
        else if (quantityPlayers.equals("three")){
            boxThreePlayers.setChecked(true);
        }
        else if (quantityPlayers.equals("four")){
            boxFourPlayers.setChecked(true);
        }
        else if (quantityPlayers.equals("five")){
            boxFivePlayers.setChecked(true);
        }
    }
     //TODO rate should is retrofit field with custom slider
    private void setRate(String rate) {
        if (rate.equals("100")){
            box100.setChecked(true);
        } else {
            box200.setChecked(true);
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() != R.id.play_button_rand) return;
        getSettingsStrings = new String[5];
        getSettingsStrings[0] = getSpeed();
        getSettingsStrings[1] = getModeCards();
        getSettingsStrings[2] = getModeRoom();
        getSettingsStrings[3] = getQuantityPlayers();
        getSettingsStrings[4] = getRate();

        OnNextWaitFragmentListener listener = (OnNextWaitFragmentListener) getActivity();
        listener.OnNextWaitFragment(getSettingsStrings);

    }
    //TODO remake gag
    private String getSpeed() {
        String preferences = "fast";
        return preferences;
    }

    private String getModeCards() {
        String modeCards = "long";
        return modeCards;
    }

    private String getModeRoom() {
        String modeRoom = "notOpen";
        return modeRoom;
    }

    private String getQuantityPlayers() {
        String quantityPlayers = "three";
        return quantityPlayers;
    }

    private String getRate() {
        String rate = "200";
        return rate;
    }

    interface OnNextWaitFragmentListener{
        void OnNextWaitFragment(String[] settingsStrings);
    }

}