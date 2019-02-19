package co.techmagic.timer.di.component;

import co.techmagic.timer.presentation.MainActivity;
import co.techmagic.timer.di.module.MainModule;
import co.techmagic.timer.di.scope.MainScope;
import dagger.Subcomponent;

/**
 * @author Ivan Savenko
 * Date: February, 17, 2019
 * Time: 10:48 PM
 */
@Subcomponent(modules = {MainModule.class})
@MainScope
public interface MainComponent {

    void inject(MainActivity activity);

}