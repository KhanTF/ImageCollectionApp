package ru.rage.image.presentation.conversions

import android.view.View
import androidx.databinding.BindingConversion

object Conversions{

    @JvmStatic
    @BindingConversion
    fun convertBooleanToVisibility(isVisible: Boolean): Int = if(isVisible) View.VISIBLE else View.GONE

}