package ru.rage.image.presentation.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.rage.image.presentation.ui.main.image.ImageListFragment
import ru.rage.image.presentation.ui.main.image.ImageListModule
import ru.rage.image.presentation.ui.main.viewer.ImageViewerFragment
import ru.rage.image.presentation.ui.main.viewer.ImageViewerModule

@Module
abstract class MainBuilder{
    @ContributesAndroidInjector(modules = [ImageViewerModule::class])
    abstract fun buildRandomImageFragment() : ImageViewerFragment
    @ContributesAndroidInjector(modules = [ImageListModule::class])
    abstract fun buildImageListFragment() : ImageListFragment
}