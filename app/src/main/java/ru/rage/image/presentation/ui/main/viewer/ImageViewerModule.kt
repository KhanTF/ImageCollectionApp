package ru.rage.image.presentation.ui.main.viewer

import dagger.Module
import dagger.Provides
import ru.rage.image.presentation.ui.base.provideViewModel
import java.io.File
import javax.inject.Named
import javax.inject.Provider

@Module
class ImageViewerModule {

    @Provides
    fun provideViewModel(viewerFragment: ImageViewerFragment, imageViewerViewModel: Provider<ImageViewerViewModel>): IImageViewerViewModel {
        return viewerFragment.provideViewModel(imageViewerViewModel)
    }

    @Provides
    @Named("ImageViewerViewModel_imagePath")
    fun provideImageArgument(viewerFragment: ImageViewerFragment): String? = ImageViewerFragmentArgs.fromBundle(viewerFragment.requireArguments()).path

}