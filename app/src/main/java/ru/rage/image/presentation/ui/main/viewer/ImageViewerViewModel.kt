package ru.rage.image.presentation.ui.main.viewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.rage.image.domain.usecase.GetRandomImageUseCase
import ru.rage.image.presentation.model.mappers.ImageModelMapper
import ru.rage.image.presentation.ui.base.BaseViewModel
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class ImageViewerViewModel @Inject constructor(private val getRandomImageUseCase: GetRandomImageUseCase,
                                               @Named("ImageViewerViewModel_imagePath")
                                               private val image: String?) : BaseViewModel(), IImageViewerViewModel {

    private val imageMutableLiveData = MutableLiveData<File>()
    private val isProgressMutableLiveData = MutableLiveData<Boolean>()
    private val errorMessageMutableLiveData = MutableLiveData<String?>()

    override fun getImage(): LiveData<File> = imageMutableLiveData

    override fun isProgress(): LiveData<Boolean> = isProgressMutableLiveData

    override fun getErrorMessage(): LiveData<String?> = errorMessageMutableLiveData

    private fun update() = if (image != null) {
        imageMutableLiveData.value = File(image)
    } else {
        getRandomImageUseCase
                .getRandomImage(600, 600)
                .map(ImageModelMapper::map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    errorMessageMutableLiveData.value = null
                    isProgressMutableLiveData.value = true
                }
                .doAfterTerminate {
                    isProgressMutableLiveData.value = false
                }
                .doOnError(Throwable::printStackTrace)
                .subscribe({
                    imageMutableLiveData.value = it.image
                }, {
                    errorMessageMutableLiveData.value = it.message
                })
                .disposeWhenClear()
    }

    init {
        update()
    }

    override fun onRetry() {
        update()
    }

}