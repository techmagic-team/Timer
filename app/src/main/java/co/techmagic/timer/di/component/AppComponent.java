package co.techmagic.timer.di.component;

import javax.inject.Singleton;

import co.techmagic.timer.di.module.AppModule;
import co.techmagic.timer.di.module.MainModule;
import co.techmagic.timer.timer.TimerManager;
import dagger.Component;

/**
 * @author Ivan Savenko
 * Date: February, 17, 2019
 * Time: 10:47 PM
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    TimerManager timerManager();

    MainComponent addMainComponent(MainModule module);

}
