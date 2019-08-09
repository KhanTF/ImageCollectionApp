package ru.rage.image.util.resources

import android.content.Context

class ResourcesManagerImpl(private val context: Context) : ResourcesManager{
    override fun getString(id: Int): String {
        return context.getString(id)
    }
}