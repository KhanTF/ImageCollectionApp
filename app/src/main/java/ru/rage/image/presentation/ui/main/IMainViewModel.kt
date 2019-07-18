package ru.rage.image.presentation.ui.main

import android.view.MenuItem
import androidx.lifecycle.LiveData
import ru.rage.image.presentation.ui.base.IBaseViewModel

interface IMainViewModel : IBaseViewModel{
    val title : LiveData<String>
    fun onNavigationClick(menuItem: MenuItem) : Boolean
}