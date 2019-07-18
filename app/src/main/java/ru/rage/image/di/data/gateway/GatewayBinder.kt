package ru.rage.image.di.data.gateway

import dagger.Binds
import dagger.Module
import ru.rage.image.data.gateway.ImageGatewayImpl
import ru.rage.image.domain.gateway.ImageGateway

@Module
abstract class GatewayBinder{

    @Binds
    abstract fun bindImageGateway(imageGatewayImpl: ImageGatewayImpl) : ImageGateway

}