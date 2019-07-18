package ru.rage.image.presentation.ui.main.image.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import ru.rage.image.R
import ru.rage.image.databinding.FragmentRandomImageBinding
import ru.rage.image.presentation.ui.base.BaseFragment
import ru.rage.image.util.permissions.PermissionState
import ru.rage.image.util.permissions.PermissionUtil
import javax.inject.Inject


class RandomImageFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: IRandomImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getNeedPermissions().observe(this, Observer { processPermissions(it) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = getBinding<FragmentRandomImageBinding>(inflater, R.layout.fragment_random_image, container)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun processPermissions(permissions: Array<String>) {
        if (permissions.isNotEmpty()) {
            PermissionUtil.requestPermission(this, 0, permissions)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            when (PermissionUtil.processRequestPermissionsResult(this, permissions, grantResults)) {
                PermissionState.GRANTED -> viewModel.onPermissionsGranted()
                PermissionState.DENIED -> viewModel.onPermissionsDenied(false)
                PermissionState.NEVER_ASK_AGAIN -> viewModel.onPermissionsDenied(true)
            }
        }
    }

}