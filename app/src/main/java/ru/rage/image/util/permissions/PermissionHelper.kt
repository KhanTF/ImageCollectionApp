package ru.rage.image.util.permissions

import android.content.Context

class PermissionHelper(private val context: Context) {
    fun getPermissionState(permissions: Array<String>): PermissionState{
        return PermissionUtil.getPermissionState(context,permissions)
    }
}