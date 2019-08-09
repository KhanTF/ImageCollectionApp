package ru.rage.image.presentation.ui.main.viewer

import androidx.lifecycle.LiveData
import ru.rage.image.presentation.ui.base.IBaseViewModel
import java.io.File

interface IImageViewerViewModel : IBaseViewModel {
    fun getImage(): LiveData<File>
    fun isProgress(): LiveData<Boolean>
    fun getErrorMessage(): LiveData<String?>
    fun onRetry()
}