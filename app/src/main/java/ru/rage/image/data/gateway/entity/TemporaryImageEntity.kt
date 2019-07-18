package ru.rage.image.data.gateway.entity

import ru.rage.image.domain.entity.ImageEntity
import java.io.File
import java.util.*

data class TemporaryImageEntity(private val dateTemporary: Date, private val imageTemporary: File) : ImageEntity {
    override val id: Long
        get() = 0L
    override val type: ImageEntity.Type
        get() = ImageEntity.Type.TEMPORARY
    override val date: Date
        get() = dateTemporary
    override val image: File
        get() = imageTemporary
}
