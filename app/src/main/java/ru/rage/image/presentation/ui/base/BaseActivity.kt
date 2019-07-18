package ru.rage.image.presentation.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import ru.rage.image.presentation.ui.base.factory.BaseViewModelFactory
import ru.rage.image.presentation.ui.base.factory.BaseViewModelFactoryHolder
import ru.rage.image.presentation.ui.base.factory.FactoryHolder
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        private const val KEY_ID = "ru.rage.image.presentation.ui.base.BaseActivity.KEY_ID"
        private const val PREFIX_ID = "ru.rage.image.presentation.ui.base.BaseActivity_"
        private const val TAG_BASE_VIEW_MODEL_FACTORY_HOLDER =
            "ru.rage.image.presentation.ui.base.factory.BaseViewModelFactoryHolder"
    }

    private var id: String = PREFIX_ID + UUID.randomUUID().toString() + toString()

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingFragmentInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            id = savedInstanceState.getString(KEY_ID, null) ?: throw IllegalArgumentException("KEY_ID not exist")
        }
        val holder = supportFragmentManager.findFragmentByTag(TAG_BASE_VIEW_MODEL_FACTORY_HOLDER)
        if (holder == null)
            supportFragmentManager
                .beginTransaction()
                .add(BaseViewModelFactoryHolder(), TAG_BASE_VIEW_MODEL_FACTORY_HOLDER)
                .commitNow()
        AndroidInjection.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_ID, id)
        super.onSaveInstanceState(outState)
    }

    fun getFactory(viewModelProvider: Provider<out ViewModel>): BaseViewModelFactory<*> {
        val holder = getFactoryHolder()
        var factory = holder.getFactory(id)
        if (factory == null) {
            factory = BaseViewModelFactory.getInstance(viewModelProvider)
            holder.putFactory(id, factory)
        }
        return factory
    }

    fun getFactoryHolder(): FactoryHolder {
        return (supportFragmentManager.findFragmentByTag(TAG_BASE_VIEW_MODEL_FACTORY_HOLDER) as? BaseViewModelFactoryHolder)
            ?: throw IllegalStateException("BaseViewModelFactoryHolder was not initiated")
    }

}