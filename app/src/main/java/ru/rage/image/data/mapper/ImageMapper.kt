package ru.rage.image.data.mapper

import ru.rage.image.data.db.entity.ImageDataItem
import ru.rage.image.data.gateway.entity.LocalImageEntity
import ru.rage.image.data.gateway.entity.TemporaryImageEntity
import ru.rage.image.domain.entity.ImageEntity
import ru.rage.image.domain.exceptions.image.ImageNotFoundException
import ru.rage.image.util.date.DateUtil
import java.io.File

object ImageMapper {
    fun mapTemporary(image: File): ImageEntity {
        return TemporaryImageEntity(DateUtil.getCurrentDate(), image)
    }
    fun mapLocal(imageDataItem: ImageDataItem) : ImageEntity{
        return LocalImageEntity(
            imageDataItem.id,
            imageDataItem.date?:throw IllegalArgumentException("Date is null"),
            imageDataItem.image.let {
                if(it== null)
                    throw ImageNotFoundException("Image is null")
                else if(!it.exists())
                    throw ImageNotFoundException("Image not exists, path = ${it.absolutePath}")
                else it
            })
    }
}