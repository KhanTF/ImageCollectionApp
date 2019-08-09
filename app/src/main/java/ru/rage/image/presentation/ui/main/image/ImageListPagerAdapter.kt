package ru.rage.image.presentation.ui.main.image

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ImageListPagerAdapter(
        fragmentManager: FragmentManager,
        private val fragmentProvider: (Int)->Fragment) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment {
        return fragmentProvider(position)
    }

    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

}