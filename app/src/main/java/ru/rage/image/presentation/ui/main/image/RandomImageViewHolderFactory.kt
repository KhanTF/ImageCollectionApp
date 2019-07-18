package ru.rage.image.presentation.ui.main.image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rage.image.domain.usecase.GetRandomImageUseCase
import javax.inject.Inject

class RandomImageViewHolderFactory @Inject constructor(
    private val getRandomImageUseCase: GetRandomImageUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == RandomImageViewModel::class.java) {
            return RandomImageViewModel(getRandomImageUseCase) as T
        }
        return super.create(modelClass)
    }
}