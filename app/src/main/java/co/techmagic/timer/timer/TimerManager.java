package co.techmagic.timer.timer;

import io.reactivex.subjects.BehaviorSubject;

/**
 * @author Ivan Savenko
 * Date: February, 17, 2019
 * Time: 9:29 PM
 */
public interface TimerManager {

    BehaviorSubject<Long> getTimerBehaviorSubject();

    void start();

    void stop();

    void reset();

    void increaseRemainingTime(long timeMs);

}
