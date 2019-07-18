package ru.rage.image.presentation.ui

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.rage.image.presentation.ui.main.MainActivity
import ru.rage.image.presentation.ui.main.MainModule

@Module
abstract class ActivityBuilder{

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun buildMainActivity() : MainActivity

}