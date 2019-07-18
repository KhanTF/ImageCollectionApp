package ru.rage.image

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ru.rage.image.di.DaggerAppComponent

class ImageApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).create(this)
    }

    override fun onCreate() {
        super.onCreate()
    }

}