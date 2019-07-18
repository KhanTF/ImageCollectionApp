package ru.rage.image.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rage.image.presentation.navigation.NavControllerRouter
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val navControllerRouter: NavControllerRouter) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass == MainViewModel::class.java){
            return MainViewModel(navControllerRouter) as T
        }
        return super.create(modelClass)
    }

}