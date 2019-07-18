package ru.rage.image.presentation.model.mappers

import ru.rage.image.domain.entity.ImageEntity
import ru.rage.image.presentation.model.ImageModel

object ImageModelMapper {
    fun map(imageEntity: ImageEntity) : ImageModel{
        return ImageModel(
            imageEntity.id,
            imageEntity.type,
            imageEntity.image,
            imageEntity.date
        )
    }
}