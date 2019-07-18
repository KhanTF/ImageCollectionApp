package ru.rage.image.presentation.ui.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.ui.NavigationUI
import ru.rage.image.R
import ru.rage.image.databinding.ActivityMainBinding
import ru.rage.image.presentation.navigation.NavControllerRouter
import ru.rage.image.presentation.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: IMainViewModel
    @Inject
    lateinit var navControllerHolder: NavControllerRouter.NavControllerHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        NavigationUI.setupWithNavController(binding.navView,navControllerHolder.getRequireController())
    }

    override fun onResume() {
        super.onResume()
        navControllerHolder.attach()
    }

    override fun onPause() {
        navControllerHolder.detach()
        super.onPause()
    }
}
