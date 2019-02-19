package co.techmagic.timer;

import android.app.Application;

import co.techmagic.timer.di.component.AppComponent;
import co.techmagic.timer.di.component.DaggerAppComponent;
import co.techmagic.timer.di.component.MainComponent;
import co.techmagic.timer.di.module.AppModule;
import co.techmagic.timer.di.module.MainModule;

/**
 * @author Ivan Savenko
 * Date: February, 17, 2019
 * Time: 8:58 PM
 */
public class TimerApp extends Application {

    private AppComponent mAppComponent;
    private MainComponent mMainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Start timer on App launch
        getAppComponent().timerManager().start();
    }

    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }

        return mAppComponent;
    }

    public void releaseAppComponent() {
        mAppComponent = null;
    }

    public MainComponent getMainComponent() {
        if (mMainComponent == null) {
            mMainComponent = getAppComponent().addMainComponent(new MainModule());
        }
        return mMainComponent;
    }

    public void releaseMainComponent() {
        mMainComponent = null;
    }
}
