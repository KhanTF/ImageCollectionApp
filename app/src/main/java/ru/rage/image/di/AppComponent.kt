package ru.rage.image.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.rage.image.ImageApplication
import ru.rage.image.di.data.DataModule
import ru.rage.image.di.data.db.DbModule
import ru.rage.image.di.navigation.NavigationModule
import ru.rage.image.di.data.network.NetworkModule
import ru.rage.image.presentation.ui.ActivityBuilder
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        NavigationModule::class,
        AppModule::class,
        DataModule::class]
)
interface AppComponent : AndroidInjector<ImageApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ImageApplication>() {
        @BindsInstance
        abstract fun application(application: Application): Builder
    }
}