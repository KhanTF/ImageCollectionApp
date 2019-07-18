package ru.rage.image.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import javax.inject.Provider

inline fun <reified T> BaseActivity.provideViewModel(viewModel: Provider<T>): T where  T : ViewModel, T : IBaseViewModel{
    return ViewModelProviders.of(this, getFactory(viewModel)).get(T::class.java).also {
        lifecycle.addObserver(it)
    }
}

inline fun <reified T> BaseFragment.provideViewModel(viewModel: Provider<T>): T where  T : ViewModel, T : IBaseViewModel{
    return ViewModelProviders.of(this, getFactory(viewModel)).get(T::class.java).also {
        lifecycle.addObserver(it)
    }
}