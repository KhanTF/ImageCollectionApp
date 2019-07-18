package ru.rage.image.data.gateway.entity

import ru.rage.image.domain.entity.ImageEntity
import java.io.File
import java.util.*

data class LocalImageEntity(private val idLocal: Long, private val dateLocal: Date, private val imageLocal: File) : ImageEntity {
    override val id: Long
        get() = idLocal
    override val type: ImageEntity.Type
        get() = ImageEntity.Type.LOCAL
    override val date: Date
        get() = dateLocal
    override val image: File
        get() = imageLocal
}
