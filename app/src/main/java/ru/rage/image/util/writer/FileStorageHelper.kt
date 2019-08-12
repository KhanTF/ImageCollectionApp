package ru.rage.image.util.writer

import java.io.File

interface FileStorageHelper {
    fun save(file: File, bytes: ByteArray, append: Boolean = false)
    fun delete(file: File)
}