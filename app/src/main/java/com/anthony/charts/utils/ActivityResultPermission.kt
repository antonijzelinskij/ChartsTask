package com.anthony.charts.utils

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class ActivityResultPermission(
    fragment: Fragment
) {
    private var permissionCallbacks: OnPermissionCallback? = null

    private val activityResultLauncher = fragment.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val callback = permissionCallbacks ?: return@registerForActivityResult

        val grantedPermissions = arrayListOf<String>()
        val declinedPermissions = arrayListOf<String>()
        val declinedForeverPermissions = arrayListOf<String>()

        permissions.map {
            val permission = it.key
            if (callback.permissions.contains(permission)) {
                when {
                    it.value -> {
                        grantedPermissions.add(permission)
                    }
                    !fragment.shouldShowRequestPermissionRationale(it.key) -> {
                        declinedForeverPermissions.add(permission)
                    }
                    else -> {
                        declinedPermissions.add(permission)
                    }
                }
            }
        }

        if (grantedPermissions.isNotEmpty()) {
            callback.onPermissionGranted(grantedPermissions.toTypedArray())
        }

        if (declinedPermissions.isNotEmpty()) {
            callback.onPermissionDeclined(declinedPermissions.toTypedArray())
        }

        if (declinedForeverPermissions.isNotEmpty()) {
            callback.onPermissionDeclinedForever(declinedForeverPermissions.toTypedArray())
        }

        permissionCallbacks = null
    }

    fun requestPermissions(vararg permissions: String): PermissionBuilder {
        return PermissionBuilder(arrayOf(*permissions), this)
    }

    private fun askPermissions() {
        permissionCallbacks?.permissions?.let {
            activityResultLauncher.launch(it)
        }
    }

    private fun addCallback(onPermissionCallback: OnPermissionCallback) {
        permissionCallbacks = onPermissionCallback
    }

    class PermissionBuilder(
        private val permissions: Array<String>,
        private val activityResultPermission: ActivityResultPermission
    ) {

        private var onPermissionGranted: ((Array<String>) -> Unit)? = null
        private var onPermissionDeclined: ((Array<String>) -> Unit)? = null
        private var onPermissionDeclinedForever: ((Array<String>) -> Unit)? = null

        fun onPermissionGranted(action: (Array<String>) -> Unit): PermissionBuilder {
            onPermissionGranted = action
            return this
        }

        fun onPermissionDeclined(action: (Array<String>) -> Unit): PermissionBuilder {
            onPermissionDeclined = action
            return this
        }

        fun onPermissionDeclinedForever(action: (Array<String>) -> Unit): PermissionBuilder {
            onPermissionDeclinedForever = action
            return this
        }

        fun ask() {
            activityResultPermission.addCallback(
                OnPermissionCallback(
                    permissions,
                    onPermissionGranted,
                    onPermissionDeclined,
                    onPermissionDeclinedForever
                )
            )
            activityResultPermission.askPermissions()
        }
    }

    private class OnPermissionCallback(
        val permissions: Array<String>,
        private val onPermissionGranted: ((Array<String>) -> Unit)?,
        private val onPermissionDeclined: ((Array<String>) -> Unit)?,
        private val onPermissionDeclinedForever: ((Array<String>) -> Unit)?
    ) {
        fun onPermissionGranted(permissions: Array<String>) {
            onPermissionGranted?.invoke(permissions)
        }

        fun onPermissionDeclined(permissions: Array<String>) {
            onPermissionDeclined?.invoke(permissions)
        }

        fun onPermissionDeclinedForever(permissions: Array<String>) {
            onPermissionDeclinedForever?.invoke(permissions)
        }
    }
}