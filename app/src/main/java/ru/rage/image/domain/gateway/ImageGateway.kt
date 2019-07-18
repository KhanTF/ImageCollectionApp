package ru.rage.image.domain.gateway

import io.reactivex.Single
import ru.rage.image.domain.entity.ImageEntity
import java.io.File

interface ImageGateway {
    fun getRandomImage(width: Int, height: Int): Single<ImageEntity>
}