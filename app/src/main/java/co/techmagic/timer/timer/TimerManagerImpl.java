package co.techmagic.timer.timer;

import android.os.CountDownTimer;
import android.util.Log;
import io.reactivex.subjects.BehaviorSubject;

/**
 * @author Ivan Savenko
 * Date: February, 17, 2019
 * Time: 9:15 PM
 */
public class TimerManagerImpl implements TimerManager {

    private final String TAG = this.getClass().getSimpleName();

    private final long INTERVAL_MS = 100L;
    private final long MAX_TIME_TO_FINISH_MS = 120000L; //2min

    private BehaviorSubject<Long> mBehaviorSubject;

    private CountDownTimer mCountDownTimer;
    private Long mTimeToFinishMs;


    public TimerManagerImpl() {
        mBehaviorSubject = BehaviorSubject.create();
        mTimeToFinishMs = MAX_TIME_TO_FINISH_MS;
    }

    @Override
    public BehaviorSubject<Long> getTimerBehaviorSubject() {
        return mBehaviorSubject;
    }

    @Override
    public void start() {
        Log.d(TAG, "start -> mTimeToFinishMs: " + mTimeToFinishMs);
        mCountDownTimer = new CountDownTimer(mTimeToFinishMs, INTERVAL_MS) {
            @Override
            public void onTick(long timeToFinishMs) {
                setTimeToFinishMs(timeToFinishMs);
            }

            @Override
            public void onFinish() {
                setTimeToFinishMs(0L);
            }
        };
        mCountDownTimer.start();
    }

    @Override
    public void stop() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    public void reset() {
        stop();
        setTimeToFinishMs(MAX_TIME_TO_FINISH_MS);
        start();
    }

    @Override
    public void increaseRemainingTime(long timeMs) {
        stop();
        if (mTimeToFinishMs + timeMs > MAX_TIME_TO_FINISH_MS) {
            setTimeToFinishMs(MAX_TIME_TO_FINISH_MS);
        } else {
            setTimeToFinishMs(mTimeToFinishMs + timeMs);
        }
        start();
    }


    private void setTimeToFinishMs(Long timeToFinishMs) {
        mTimeToFinishMs = timeToFinishMs;
        mBehaviorSubject.onNext(mTimeToFinishMs);
    }

}
