package ru.rage.image.presentation.ui.main.image

import android.Manifest
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.rage.image.R
import ru.rage.image.presentation.ui.base.BaseViewModel
import ru.rage.image.presentation.ui.main.viewer.ImageViewerFragment
import ru.rage.image.presentation.ui.main.viewer.ImageViewerFragmentArgs
import ru.rage.image.util.mvvm.live.SingleLiveData
import ru.rage.image.util.permissions.PermissionHelper
import ru.rage.image.util.permissions.PermissionState
import ru.rage.image.util.resources.ResourcesManager
import javax.inject.Inject

class ImageListViewModel @Inject constructor(
        private val permissionHelper: PermissionHelper,
        private val resourcesManager: ResourcesManager) : BaseViewModel(), IImageListViewModel{

    companion object{
        private val PERMISSION = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    private val imageViewerProviderMutableLiveData = SingleLiveData<((Int) -> Fragment)?>()
    private val needPermissionMutableLiveData = SingleLiveData<Array<String>?>()
    private val errorMessageMutableLiveData = MutableLiveData<String?>()

    override fun getImageViewerProvider(): LiveData<((Int) -> Fragment)?> = imageViewerProviderMutableLiveData
    override fun getNeedPermissions(): LiveData<Array<String>?> = needPermissionMutableLiveData
    override fun getErrorMessage(): LiveData<String?> = errorMessageMutableLiveData

    init {
        if(permissionHelper.getPermissionState(PERMISSION) == PermissionState.GRANTED) {
            imageViewerProviderMutableLiveData.value = { ImageViewerFragment.getInstance(ImageViewerFragmentArgs()) }
            needPermissionMutableLiveData.value = null
            errorMessageMutableLiveData.value = null
        }else{
            imageViewerProviderMutableLiveData.value = null
            errorMessageMutableLiveData.value = null
            needPermissionMutableLiveData.value = PERMISSION
        }
    }

    override fun onPermissionStateChanged() {
        when (permissionHelper.onPermissionChangeState(PERMISSION)) {
            PermissionState.GRANTED -> {
                imageViewerProviderMutableLiveData.value = { ImageViewerFragment.getInstance(ImageViewerFragmentArgs()) }
                needPermissionMutableLiveData.value = null
                errorMessageMutableLiveData.value = null
            }
            else -> {
                errorMessageMutableLiveData.value = resourcesManager.getString(R.string.error_need_permissions)
            }
        }
    }

}