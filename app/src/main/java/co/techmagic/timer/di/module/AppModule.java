package co.techmagic.timer.di.module;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import co.techmagic.timer.TimerApp;
import co.techmagic.timer.timer.TimerManager;
import co.techmagic.timer.timer.TimerManagerImpl;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Ivan Savenko
 * Date: February, 17, 2019
 * Time: 10:48 PM
 */
@Module
public class AppModule {

    private TimerApp app;

    public AppModule(TimerApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    public TimerManager provideTimerManager() {
        return new TimerManagerImpl();
    }

}
