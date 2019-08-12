package ru.rage.image.presentation.ui.main.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_image_list.*
import ru.rage.image.R
import ru.rage.image.databinding.FragmentImageListBinding
import ru.rage.image.presentation.common.helpers.ZoomOutPageTransformer
import ru.rage.image.presentation.ui.base.BaseFragment
import javax.inject.Inject

class ImageListFragment : BaseFragment(){

    companion object{
        private const val REQUEST_CODE_PERMISSIONS = 0
    }

    @Inject
    lateinit var viewModel : IImageListViewModel

    private val permissionObserver = Observer<Array<String>?> {
        if(it!=null){
            requestPermissions(it, REQUEST_CODE_PERMISSIONS)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getImageViewerProvider().observe(this, Observer {
            if(it!=null) {
                val adapter = ImageListPagerAdapter(childFragmentManager, it)
                image_pager.adapter = adapter
            }else{
                image_pager.adapter = null
            }
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getNeedPermissions().observe(this, permissionObserver)
    }

    override fun onStop() {
        super.onStop()
        viewModel.getNeedPermissions().removeObserver(permissionObserver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = getBinding<FragmentImageListBinding>(inflater, R.layout.fragment_image_list, container)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        image_pager.setPageTransformer(true, ZoomOutPageTransformer())
        image_pager.offscreenPageLimit = 2
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        viewModel.onPermissionStateChanged()
    }

}