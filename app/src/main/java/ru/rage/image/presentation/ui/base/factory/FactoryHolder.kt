package ru.rage.image.presentation.ui.base.factory

import androidx.lifecycle.ViewModel

interface FactoryHolder{
    fun getFactory(key: String) : BaseViewModelFactory<*>?
    fun putFactory(key: String, factory: BaseViewModelFactory<*>)
    fun removeFactory(key: String)
}