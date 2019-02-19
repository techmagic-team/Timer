package co.techmagic.timer.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import co.techmagic.timer.presentation.MainPresenter;
import co.techmagic.timer.presentation.MainPresenterImpl;
import co.techmagic.timer.timer.TimerManager;
import co.techmagic.timer.di.scope.MainScope;
import dagger.Module;
import dagger.Provides;

/**
 * @author Ivan Savenko
 * Date: February, 17, 2019
 * Time: 10:49 PM
 */
@Module
public class MainModule {

    @Provides
    @NonNull
    @MainScope
    MainPresenter provideMainPresenter(Context appContext, TimerManager timerManager) {
        return new MainPresenterImpl(appContext, timerManager);
    }


}
