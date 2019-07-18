package ru.rage.image.di.navigation

import dagger.Module
import dagger.Provides
import ru.rage.image.presentation.navigation.NavControllerRouter
import javax.inject.Singleton

@Module
class NavigationModule{

    @Singleton
    @Provides
    fun provideNavControllerRouter() = NavControllerRouter()

}