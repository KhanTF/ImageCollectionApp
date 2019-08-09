package ru.rage.image.util.permissions

import androidx.lifecycle.*

class PermissionLiveData(private val permission: Array<String>, private val permissionHelper: PermissionHelper) : MutableLiveData<Array<String>?>(), LifecycleObserver {

    override fun observe(owner: LifecycleOwner, observer: Observer<in Array<String>?>) {
        owner.lifecycle.addObserver(this)
        super.observe(owner, observer)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onCheckPermission() {
        if (permissionHelper.getPermissionState(permission) == PermissionState.GRANTED) {
            value = null
        } else {
            value = permission
        }
    }

}