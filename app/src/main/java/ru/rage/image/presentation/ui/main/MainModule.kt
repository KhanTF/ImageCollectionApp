package ru.rage.image.presentation.ui.main

import dagger.Module
import dagger.Provides
import ru.rage.image.R
import ru.rage.image.presentation.navigation.NavControllerRouter
import ru.rage.image.presentation.ui.base.provideViewModel
import ru.rage.image.util.permissions.PermissionHelper
import ru.rage.image.util.resources.ResourcesManager
import ru.rage.image.util.resources.ResourcesManagerImpl
import javax.inject.Provider

@Module(includes = [MainBuilder::class])
class MainModule{

    @Provides
    fun provideViewModel(mainActivity: MainActivity,mainViewModel: Provider<MainViewModel>) : IMainViewModel {
        return mainActivity.provideViewModel(mainViewModel)
    }

    @Provides
    fun provideNavControllerHolder(navControllerRouter: NavControllerRouter, mainActivity: MainActivity) : NavControllerRouter.NavControllerHolder = NavControllerRouter.NavControllerHolder(navControllerRouter,mainActivity, R.id.nav_host_fragment)

    @Provides
    fun providePermissionHelper(mainActivity: MainActivity) : PermissionHelper = PermissionHelper(mainActivity)

    @Provides
    fun provideResourcesManager(mainActivity: MainActivity) : ResourcesManager = ResourcesManagerImpl(mainActivity)

}