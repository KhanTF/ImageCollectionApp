package ru.rage.image.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.rage.image.data.db.dao.ImageDao
import ru.rage.image.data.db.entity.ImageDataItem

@Database(entities = [ImageDataItem::class], version = 1)
abstract class ImageDatabase : RoomDatabase(){
    abstract fun getImageDataDao(): ImageDao
}