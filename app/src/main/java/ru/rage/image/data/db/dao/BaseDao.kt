package ru.rage.image.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import io.reactivex.Single

@Dao
interface BaseDao<T>{
    @Insert
    fun insert(data: T): Single<Long>
    @Update
    fun update(data: T): Single<Int>
    @Delete
    fun delete(data: T): Single<Int>
}