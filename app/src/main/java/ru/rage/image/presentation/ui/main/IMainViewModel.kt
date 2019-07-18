package ru.rage.image.presentation.ui.main

import android.view.MenuItem
import androidx.lifecycle.LiveData

interface IMainViewModel{
    val title : LiveData<String>
    fun onNavigationClick(menuItem: MenuItem) : Boolean
}