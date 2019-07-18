package ru.rage.image.presentation.ui.main

import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.multibindings.IntoMap
import ru.rage.image.presentation.navigation.NavControllerRouter
import javax.inject.Inject
 
class MainViewModel @Inject constructor(private val navControllerRouter: NavControllerRouter) : ViewModel(), IMainViewModel {

    private val titleMutableLiveData = MutableLiveData<String>()

    override val title: LiveData<String>
        get() =  titleMutableLiveData

    init {
        titleMutableLiveData.value = "I'm Yura Khan"
    }

    override fun onNavigationClick(menuItem: MenuItem): Boolean {
        navControllerRouter.perform {

        }
        return true
    }

}