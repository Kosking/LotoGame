package my.game.loto.initialAction.screens;

import my.game.loto.initialAction.retrofit.settingsObjects.FullGameObject;
import my.game.loto.initialAction.retrofit.settingsObjects.NewPlayerData;
import my.game.loto.initialAction.retrofit.settingsObjects.PrimaryData;

public interface InitialView {

    void nextWelcomeFragment();

    void showLoadingError();

    void nextGameActivity(FullGameObject fullGameObject);

    void nextChoiceActivity(PrimaryData primaryData);

    void freshChoiceActivity(NewPlayerData newPlayerData);
}
