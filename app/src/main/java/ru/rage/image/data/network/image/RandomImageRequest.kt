package ru.rage.image.data.network.image

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface RandomImageRequest {
    @GET("/{width}/{height}")
    @Streaming
    fun getRandomImage(@Path("width") width: Int,@Path("height")  height: Int) : Observable<ResponseBody>
}