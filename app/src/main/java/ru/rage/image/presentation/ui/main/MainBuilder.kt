package ru.rage.image.presentation.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.rage.image.presentation.ui.main.image.random.RandomImageFragment
import ru.rage.image.presentation.ui.main.image.random.RandomImageModule

@Module
abstract class MainBuilder{
    @ContributesAndroidInjector(modules = [RandomImageModule::class])
    abstract fun buildRandomImageFragment() : RandomImageFragment
}