package ru.rage.image.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single
import ru.rage.image.data.db.entity.ImageDataItem

@Dao
interface ImageDao : BaseDao<ImageDataItem> {

    @Query("SELECT * FROM images")
    fun getImageList(): Single<List<ImageDataItem>>

    @Query("SELECT * FROM images WHERE id=:id")
    fun getImageById(id: Long): Single<ImageDataItem>

}