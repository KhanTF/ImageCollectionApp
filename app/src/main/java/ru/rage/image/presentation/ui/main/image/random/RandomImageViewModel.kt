package ru.rage.image.presentation.ui.main.image.random

import android.Manifest
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.rage.image.domain.usecase.GetRandomImageUseCase
import ru.rage.image.presentation.model.mappers.ImageModelMapper
import ru.rage.image.presentation.ui.base.BaseViewModel
import ru.rage.image.util.permissions.PermissionHelper
import ru.rage.image.util.permissions.PermissionState
import java.io.File
import javax.inject.Inject

class RandomImageViewModel @Inject constructor(
    private val permissionHelper: PermissionHelper,
    private val getRandomImageUseCase: GetRandomImageUseCase
) : BaseViewModel(),
    IRandomImageViewModel {

    companion object {
        private val PERMISSIONS =
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private val needPermissionsLiveData = MutableLiveData<Array<String>>()
    private val imageMutableLiveData = MutableLiveData<File>()
    private val isProgressMutableLiveData = MutableLiveData<Boolean>()

    override fun isProgress(): LiveData<Boolean> = isProgressMutableLiveData

    override fun getImage(): LiveData<File> = imageMutableLiveData

    override fun getNeedPermissions(): LiveData<Array<String>> {
        return needPermissionsLiveData
    }

    private fun getNeedPermissionsInternal()  = if (permissionHelper.getPermissionState(PERMISSIONS) == PermissionState.GRANTED) emptyArray() else PERMISSIONS

    init {
        if (getNeedPermissionsInternal().isEmpty())
            loadImage()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun onCheckPermissions() {
        needPermissionsLiveData.postValue(getNeedPermissionsInternal())
    }

    override fun onPermissionsGranted() {
        loadImage()
    }

    override fun onPermissionsDenied(isNeverAskAgain: Boolean) {
        if (isNeverAskAgain) {

        } else {

        }
    }

    private fun loadImage() {
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