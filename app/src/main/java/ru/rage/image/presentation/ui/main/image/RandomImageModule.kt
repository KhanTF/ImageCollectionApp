package ru.rage.image.presentation.ui.main.image

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

@Module
class RandomImageModule{

    @Provides
    fun provideViewModel(randomImageViewHolderFactory: RandomImageViewHolderFactory, fragment: RandomImageFragment) : IRandomImageViewModel{
        return ViewModelProviders.of(fragment,randomImageViewHolderFactory).get(RandomImageViewModel::class.java)
    }

}