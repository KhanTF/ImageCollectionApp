package ru.rage.image.di.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.rage.image.data.db.ImageDatabase
import ru.rage.image.data.db.dao.ImageDao
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun provideImageDatabase(context: Context): ImageDatabase = Room.databaseBuilder(context, ImageDatabase::class.java, "image.db").build()

    @Provides
    fun provideImageDataDao(imageDatabase: ImageDatabase): ImageDao = imageDatabase.getImageDataDao()

}