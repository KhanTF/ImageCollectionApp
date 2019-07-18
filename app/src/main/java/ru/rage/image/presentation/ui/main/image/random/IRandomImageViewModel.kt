package ru.rage.image.presentation.ui.main.image.random

import androidx.lifecycle.LiveData
import ru.rage.image.presentation.ui.base.IBaseViewModel
import java.io.File

interface IRandomImageViewModel : IBaseViewModel {
    fun getImage(): LiveData<File>
    fun isProgress(): LiveData<Boolean>
    fun getNeedPermissions() : LiveData<Array<String>>

    fun onCheckPermissions()
    fun onPermissionsGranted()
    fun onPermissionsDenied(isNeverAskAgain: Boolean)
}