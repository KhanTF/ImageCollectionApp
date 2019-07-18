package ru.rage.image.util.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

object PermissionUtil {

    private fun getState(state: Int): PermissionState = when (state) {
        PackageManager.PERMISSION_GRANTED -> PermissionState.GRANTED
        else -> PermissionState.DENIED
    }

    fun getPermissionState(context: Context, permissions: Array<String>): PermissionState {
        for (permission in permissions) {
            val state = getState(ActivityCompat.checkSelfPermission(context, permission))
            if (state != PermissionState.GRANTED) return state
        }
        return PermissionState.GRANTED
    }

    fun getPermissionState(fragment: Fragment, permissions: Array<String>): PermissionState {
        return getPermissionState(fragment.requireActivity(), permissions)
    }

    fun requestPermission(activity: Activity, requestCode: Int, permissions: Array<String>) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    fun requestPermission(fragment: Fragment, requestCode: Int, permissions: Array<String>) {
        fragment.requestPermissions(permissions, requestCode)
    }

    fun processRequestPermissionsResult(activity: Activity, permissions: Array<out String>, grantResults: IntArray): PermissionState {
        for (i in 0 until permissions.size) {
            val permission = permissions[i]
            val grantResult = grantResults[i]
            val state = getState(grantResult)
            if (state == PermissionState.DENIED) {
                return if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    PermissionState.DENIED
                } else {
                    PermissionState.NEVER_ASK_AGAIN
                }
            }
        }
        return PermissionState.GRANTED
    }

    fun processRequestPermissionsResult(fragment: Fragment, permissions: Array<out String>, grantResults: IntArray): PermissionState {
        return processRequestPermissionsResult(fragment.requireActivity(), permissions, grantResults)
    }


}