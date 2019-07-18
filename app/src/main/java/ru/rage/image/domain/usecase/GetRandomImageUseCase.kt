package ru.rage.image.domain.usecase

import io.reactivex.Single
import ru.rage.image.domain.entity.ImageEntity
import ru.rage.image.domain.gateway.ImageGateway
import javax.inject.Inject

class GetRandomImageUseCase @Inject constructor(private val imageGateway: ImageGateway) {
    fun getRandomImage(width: Int, height: Int): Single<ImageEntity> {
        return imageGateway.getRandomImage(width, height)
    }
}