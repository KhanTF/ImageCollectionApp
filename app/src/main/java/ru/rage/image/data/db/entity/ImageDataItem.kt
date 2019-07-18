package ru.rage.image.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.rage.image.data.db.converter.DateTimeConverter
import ru.rage.image.data.db.converter.FileConverter
import java.io.File
import java.util.*

@Entity(tableName = "images")
class ImageDataItem(
    @field:PrimaryKey(autoGenerate = true) val id: Long = 0,
    @field:TypeConverters(DateTimeConverter::class)
    var date: Date?=null,
    @field:TypeConverters(FileConverter::class)
    var image: File?=null
)