package ru.rage.image.presentation.ui.base.factory

import android.os.Bundle
import androidx.fragment.app.Fragment

class BaseViewModelFactoryHolder : Fragment(), FactoryHolder {

    private val map: MutableMap<String, BaseViewModelFactory<*>> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun putFactory(key: String, factory: BaseViewModelFactory<*>) {
        map[key] = factory
    }

    override fun removeFactory(key: String) {
        map.remove(key)
    }

    override fun getFactory(key: String): BaseViewModelFactory<*>? {
        return map[key]
    }

}