package ru.rage.image.data.gateway

import android.content.Context
import io.reactivex.Single
import ru.rage.image.data.db.dao.ImageDao
import ru.rage.image.data.mapper.ImageMapper
import ru.rage.image.data.network.image.RandomImageRequest
import ru.rage.image.domain.entity.ImageEntity
import ru.rage.image.domain.gateway.ImageGateway
import ru.rage.image.util.cache.FileStorageHelper
import java.io.File
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageGatewayImpl @Inject constructor(
    private val context: Context,
    private val fileStorageHelper: FileStorageHelper,
    private val randomImageRequest: RandomImageRequest,
    private val imageDao: ImageDao
) : ImageGateway {

    companion object {
        private const val IMAGE = "image"
    }

    private fun getDirectory(): File {
        return File(context.getExternalFilesDir(null), IMAGE).apply {
            if (!exists())
                mkdirs()
        }
    }

    private fun getUniqueFile(): File {
        return File(getDirectory(), UUID.randomUUID().toString())
    }

    private fun getFile(): File {
        var file = getUniqueFile()
        while (file.exists()) {
            file = getUniqueFile()
        }
        return file.apply {
            createNewFile()
        }
    }

    override fun getRandomImage(width: Int, height: Int): Single<ImageEntity> {
        return randomImageRequest
            .getRandomImage(width, height)
            .collect({
                getFile()
            }, { file, response ->
                fileStorageHelper.save(file, response.bytes(), true)
            })
            .map(ImageMapper::mapTemporary)
    }

    override fun getImage(id: Long): Single<ImageEntity> {
        return imageDao.getImageById(id).map(ImageMapper::mapLocal)
    }

}