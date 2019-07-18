package ru.rage.image.presentation.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel(), IBaseViewModel {

    private val clearCompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        clearCompositeDisposable.clear()
    }

    protected fun Disposable.disposeWhenClear(){
        clearCompositeDisposable.add(this)
    }

}