package ru.rage.image.di.data

import dagger.Module
import dagger.Provides
import ru.rage.image.di.data.db.DbModule
import ru.rage.image.di.data.gateway.GatewayBinder
import ru.rage.image.di.data.network.NetworkModule
import ru.rage.image.util.cache.AndroidFileStorageHelper
import ru.rage.image.util.cache.FileStorageHelper
import java.nio.file.FileStore

@Module(includes = [NetworkModule::class, GatewayBinder::class, DbModule::class])
class DataModule{

    @Provides
    fun provideFileStorageHelper() : FileStorageHelper = AndroidFileStorageHelper()

}