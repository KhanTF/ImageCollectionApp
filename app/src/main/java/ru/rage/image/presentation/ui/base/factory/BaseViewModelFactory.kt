package ru.rage.image.presentation.ui.base.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

class BaseViewModelFactory<T : ViewModel>private constructor(private val viewModel: Provider<T>) : ViewModelProvider.NewInstanceFactory(){

    companion object {
        fun<T : ViewModel> getInstance(viewModel: Provider<T>): BaseViewModelFactory<T> =
            BaseViewModelFactory(viewModel)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel.get() as T
    }

}