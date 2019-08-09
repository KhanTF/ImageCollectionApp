package ru.rage.image.presentation.ui.main.viewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import ru.rage.image.R
import ru.rage.image.databinding.FragmentImageViewerBinding
import ru.rage.image.presentation.ui.base.BaseFragment
import javax.inject.Inject


class ImageViewerFragment : BaseFragment() {

    companion object {
        fun getInstance(args: ImageViewerFragmentArgs): ImageViewerFragment {
            return ImageViewerFragment().apply {
                arguments = args.toBundle()
            }
        }
    }

    @Inject
    lateinit var viewModel: IImageViewerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = getBinding<FragmentImageViewerBinding>(inflater, R.layout.fragment_image_viewer, container)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getErrorMessage().observe(this, Observer {

        })
    }

}