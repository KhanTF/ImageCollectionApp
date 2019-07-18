package ru.rage.image.presentation.ui.main.image

import androidx.lifecycle.LiveData
import java.io.File

interface IRandomImageViewModel {
    fun getImage(): LiveData<File>
    fun isProgress(): LiveData<Boolean>

    fun onPermissionPrepared()
    fun onPermissionsGranted()
    fun onPermissionsDenied(isNeverAskAgain: Boolean)
}