package ru.rage.image.data.db.converter

import androidx.room.TypeConverter
import java.io.File

class FileConverter{
    @TypeConverter
    fun fromPathToFile(path: String) : File = File(path)
    @TypeConverter
    fun fromFileToPath(file: File) : String = file.absolutePath
}