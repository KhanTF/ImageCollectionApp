package ru.rage.image.presentation.ui.main.image

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import ru.rage.image.presentation.ui.base.IBaseViewModel

interface IImageListViewModel: IBaseViewModel {
    fun getImageViewerProvider() : LiveData<((Int)->Fragment)?>
    fun getNeedPermissions(): LiveData<Array<String>?>
    fun getErrorMessage(): LiveData<String?>
    fun onPermissionStateChanged()
}