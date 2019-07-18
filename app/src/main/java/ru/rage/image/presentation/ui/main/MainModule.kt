package ru.rage.image.presentation.ui.main

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import ru.rage.image.R
import ru.rage.image.presentation.navigation.NavControllerRouter

@Module(includes = [MainBuilder::class])
class MainModule{

    @Provides
    fun provideViewModel(mainActivity: MainActivity, mainViewModelFactory: MainViewModelFactory) : IMainViewModel {
        return ViewModelProviders.of(mainActivity,mainViewModelFactory).get(MainViewModel::class.java)
    }

    @Provides
    fun provideNavControllerHolder(navControllerRouter: NavControllerRouter, mainActivity: MainActivity) : NavControllerRouter.NavControllerHolder = NavControllerRouter.NavControllerHolder(navControllerRouter,mainActivity, R.id.nav_host_fragment)

}