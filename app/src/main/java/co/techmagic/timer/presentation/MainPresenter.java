package co.techmagic.timer.presentation;

/**
 * @author Ivan Savenko
 * Date: February, 17, 2019
 * Time: 9:56 PM
 */
public interface MainPresenter {

    void setView(MainView view);

    void onStart();

    void onStop();

    void onIncreaseRemainingTimeClick();

}
