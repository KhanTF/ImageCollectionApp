package ru.rage.image.presentation.ui.main.image

import dagger.Module
import dagger.Provides
import ru.rage.image.presentation.ui.base.provideViewModel
import javax.inject.Provider

@Module
class ImageListModule {

    @Provides
    fun provideViewModel(imageListFragment: ImageListFragment, imageListViewModel: Provider<ImageListViewModel>): IImageListViewModel {
        return imageListFragment.provideViewModel(imageListViewModel)
    }

}