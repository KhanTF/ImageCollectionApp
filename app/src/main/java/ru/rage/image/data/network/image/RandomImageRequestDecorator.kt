package ru.rage.image.data.network.image

import io.reactivex.Observable
import okhttp3.ResponseBody

abstract class RandomImageRequestDecorator(private val randomImageRequest: RandomImageRequest) : RandomImageRequest {
    override fun getRandomImage(width: Int, height: Int): Observable<ResponseBody> {
        return randomImageRequest.getRandomImage(width, height)
    }
}