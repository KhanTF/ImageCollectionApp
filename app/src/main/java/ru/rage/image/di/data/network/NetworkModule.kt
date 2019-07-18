package ru.rage.image.di.data.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import ru.rage.image.data.network.image.ExceptionRandomImageRequest
import ru.rage.image.data.network.image.RandomImageRequest
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun provideRandomImageRequest(okHttpClient: OkHttpClient): RandomImageRequest = ExceptionRandomImageRequest(
        Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(RandomImageRequest::class.java)
    )

}