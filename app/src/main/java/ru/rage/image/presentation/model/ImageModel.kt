package ru.rage.image.presentation.model

import ru.rage.image.domain.entity.ImageEntity
import java.io.File
import java.util.*

data class ImageModel(val id: Long, val type: ImageEntity.Type, val image: File, val date: Date)