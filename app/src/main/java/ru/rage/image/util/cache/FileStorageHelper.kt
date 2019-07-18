package ru.rage.image.util.cache

import java.io.File

interface FileStorageHelper {
    fun save(file: File, bytes: ByteArray, append: Boolean = false)
    fun delete(file: File)
}