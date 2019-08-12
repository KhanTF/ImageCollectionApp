package ru.rage.image.util.writer

import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class AndroidFileStorageHelper : FileStorageHelper{
    override fun save(file: File, bytes: ByteArray, append: Boolean) {
        val output = BufferedOutputStream(FileOutputStream(file,append))
        output.use {
            it.write(bytes)
            it.flush()
        }
    }

    override fun delete(file: File) {
        file.delete()
    }

}