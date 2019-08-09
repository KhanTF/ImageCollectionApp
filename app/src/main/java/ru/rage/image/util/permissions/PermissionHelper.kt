package ru.rage.image.util.permissions

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

class PermissionHelper(private val activity: Activity) {

    constructor(fragment: Fragment) : this(fragment.requireActivity())

    fun getPermissionState(permissions: Array<String>): PermissionState{
        return PermissionUtil.getPermissionState(activity,permissions)
    }
    fun onPermissionChangeState(permissions: Array<String>): PermissionState{
        return PermissionUtil.processRequestPermissionsResult(activity,permissions)
    }
}