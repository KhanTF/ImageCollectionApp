package ru.rage.image.presentation.ui.main.image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.rage.image.domain.usecase.GetRandomImageUseCase
import ru.rage.image.presentation.model.mappers.ImageModelMapper
import ru.rage.image.presentation.ui.base.BaseViewModel
import java.io.File

class RandomImageViewModel(private val getRandomImageUseCase: GetRandomImageUseCase) : BaseViewModel(),
    IRandomImageViewModel {

    private val imageMutableLiveData = MutableLiveData<File>()
    private val isProgressMutableLiveData = MutableLiveData<Boolean>()

    override fun isProgress(): LiveData<Boolean>  = isProgressMutableLiveData

    override fun getImage(): LiveData<File> = imageMutableLiveData

    override fun onPermissionPrepared() {
        loadImage()
    }

    override fun onPermissionsGranted() {
        loadImage()
    }

    override fun onPermissionsDenied(isNeverAskAgain: Boolean) {
        if(isNeverAskAgain){

        }else{

        }
    }

    private fun loadImage(){
        getRandomImageUseCase
            .getRandomImage(600, 600)
            .map(ImageModelMapper::map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isProgressMutableLiveData.value = true }
            .doAfterTerminate { isProgressMutableLiveData.value = false }
            .subscribe({
                imageMutableLiveData.value = it.image
            }, {
                it.printStackTrace()
            })
            .disposeWhenClear()
    }

}