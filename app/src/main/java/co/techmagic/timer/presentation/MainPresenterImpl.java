package co.techmagic.timer.presentation;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import co.techmagic.timer.R;
import co.techmagic.timer.timer.TimerManager;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Ivan Savenko
 * Date: February, 17, 2019
 * Time: 9:55 PM
 */
public class MainPresenterImpl implements MainPresenter {

    private final String TAG = this.getClass().getSimpleName();

    private Context mAppContext;
    private MainView mMainView;
    private TimerManager mTimerManager;
    private Disposable mTimerDisposable;

    public MainPresenterImpl(Context appContext, TimerManager timerManager) {
        mAppContext = appContext;
        mTimerManager = timerManager;
    }

    @Override
    public void setView(MainView view) {
        mMainView = view;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart...");
        mTimerManager.getTimerBehaviorSubject()
                .map(timeMs -> {
                        if (timeMs > 0) {
                            return timeToString(timeMs);
                        } else {
                            return mAppContext.getString(R.string.title_done);
                        }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        mTimerDisposable = disposable;
                    }

                    @Override
                    public void onNext(String time) {
                        if (mMainView != null) {
                            mMainView.setTime(time);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e(TAG, "error: " + throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop...");
        if (mTimerDisposable != null && !mTimerDisposable.isDisposed()) {
            mTimerDisposable.dispose();
        }
    }

    @Override
    public void onIncreaseRemainingTimeClick() {
        mTimerManager.increaseRemainingTime(10000);
    }

    private String timeToString(long timeMs) {
        long min = TimeUnit.MILLISECONDS.toMinutes(timeMs);
        long timeInSec = TimeUnit.MILLISECONDS.toSeconds(timeMs);
        long sec = timeInSec - TimeUnit.MINUTES.toSeconds(min);
        long timeInMs = TimeUnit.MILLISECONDS.toMillis(timeMs);
        long millis = timeInMs - TimeUnit.MINUTES.toMillis(min) - TimeUnit.SECONDS.toMillis(sec);
        return timeToString(min, sec, millis);
    }

    private String timeToString(long min, long sec, long millis) {
        if (min == 0) {
            return mAppContext.getString(R.string.placeholder_time_short,sec, millis / 100);

        } else {
            return mAppContext.getString(R.string.placeholder_time, min, sec, millis / 100);
        }
    }

}
