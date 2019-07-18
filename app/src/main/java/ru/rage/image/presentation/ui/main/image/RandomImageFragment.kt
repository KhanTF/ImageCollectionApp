package ru.rage.image.presentation.ui.main.image

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import ru.rage.image.R
import ru.rage.image.databinding.FragmentRandomImageBinding
import ru.rage.image.presentation.ui.base.BaseFragment
import ru.rage.image.util.permissions.PermissionState
import ru.rage.image.util.permissions.PermissionUtil
import javax.inject.Inject


class RandomImageFragment : BaseFragment() {

    companion object {
        private val PERMISSIONS = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    @Inject
    lateinit var viewModel: IRandomImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PermissionUtil.getPermissionState(this, PERMISSIONS) == PermissionState.GRANTED)
            viewModel.onPermissionPrepared()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = getBinding<FragmentRandomImageBinding>(inflater, R.layout.fragment_random_image, container)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (PermissionUtil.getPermissionState(this, PERMISSIONS) != PermissionState.GRANTED) {
            PermissionUtil.requestPermission(this, 0, PERMISSIONS)
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