package ru.rage.image.presentation.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import ru.rage.image.presentation.ui.base.factory.BaseViewModelFactory
import ru.rage.image.presentation.ui.base.factory.FactoryHolder
import java.util.*
import javax.inject.Inject
import javax.inject.Provider

abstract class BaseFragment : Fragment(), HasSupportFragmentInjector {

    companion object {
        private const val KEY_ID = "ru.rage.image.presentation.ui.base.BaseFragment.KEY_ID"
        private const val PREFIX_ID = "ru.rage.image.presentation.ui.base.BaseFragment_"
    }

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    private var id: String = PREFIX_ID + UUID.randomUUID().toString() + toString()

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingFragmentInjector
    }

    protected inline fun <reified T : ViewDataBinding> getBinding(
        layoutInflater: LayoutInflater,
        layoutId: Int,
        container: ViewGroup?
    ): T {
        return DataBindingUtil.inflate<T>(layoutInflater, layoutId, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            id = savedInstanceState.getString(KEY_ID, null) ?: throw IllegalArgumentException("KEY_ID not exist")
        }
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

    private fun getBaseActivity(): BaseActivity {
        return requireActivity().let {
            (it as? BaseActivity)
                ?: throw IllegalArgumentException("Parent activity of BaseFragment is not BaseActivity")
        }
    }

    private fun getFactoryHolder(): FactoryHolder {
        return getBaseActivity().getFactoryHolder()
    }

}