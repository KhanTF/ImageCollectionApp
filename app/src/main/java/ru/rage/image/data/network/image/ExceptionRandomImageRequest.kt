package ru.rage.image.data.network.image

import io.reactivex.Observable
import io.reactivex.functions.Function
import okhttp3.ResponseBody
import retrofit2.HttpException
import ru.rage.image.domain.exceptions.DomainException
import ru.rage.image.domain.exceptions.image.ImageDownloadException
import ru.rage.image.domain.exceptions.NotInternetConnectionException
import ru.rage.image.domain.exceptions.UnexpectedException
import java.io.IOException

class ExceptionRandomImageRequest(randomImageRequest: RandomImageRequest) : RandomImageRequestDecorator(randomImageRequest) {

    private class ErrorFunctionObservable<T> : Function<Throwable, Observable<T>> {
        override fun apply(t: Throwable): Observable<T> {
            return Observable.error(
                when (t) {
                    is DomainException -> t
                    is HttpException -> ImageDownloadException(t)
                    is IOException -> NotInternetConnectionException(t)
                    else -> UnexpectedException(t)
                }
            )
        }
    }

    override fun getRandomImage(width: Int, height: Int): Observable<ResponseBody> {
        return super.getRandomImage(width, height).onErrorResumeNext(ErrorFunctionObservable())
    }
}