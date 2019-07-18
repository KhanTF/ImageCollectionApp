package ru.rage.image.presentation.ui.main.image.random

import dagger.Module
import dagger.Provides
import ru.rage.image.presentation.ui.base.provideViewModel
import javax.inject.Provider

@Module
class RandomImageModule{

    @Provides
    fun provideViewModel(fragment: RandomImageFragment,randomImageViewModel: Provider<RandomImageViewModel>) : IRandomImageViewModel {
        return fragment.provideViewModel(randomImageViewModel)
    }

}