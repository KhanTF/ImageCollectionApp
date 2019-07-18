package ru.rage.image.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.rage.image.util.permissions.PermissionHelper

@Module
class AppModule {

    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    fun providePermissionHelper(application: Application) : PermissionHelper = PermissionHelper(application)

}