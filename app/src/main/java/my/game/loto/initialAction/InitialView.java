package my.game.loto.initialAction;

public interface InitialView {

    void nextWelcomeFragment();

    void showLoadingError();

    void nextGameActivity(FullGameObject fullGameObject);

    void nextChoiceActivity(PrimaryData primaryData);
}
