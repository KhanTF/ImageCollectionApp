package ru.rage.image.presentation.binding

import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.google.android.material.bottomnavigation.BottomNavigationView

@BindingMethods(
    BindingMethod(
        type = BottomNavigationView::class,
        attribute = "app:onNavigationItemSelected",
        method = "setOnNavigationItemSelectedListener")
)
class BottomNavigationViewBindingAdapter