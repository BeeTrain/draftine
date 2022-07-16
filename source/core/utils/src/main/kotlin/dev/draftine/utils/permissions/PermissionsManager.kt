package dev.draftine.utils.permissions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dev.draftine.utils.lifecycle.ActivityContextProvider
import dev.draftine.utils.permissions.model.Permission

private val TAG = PermissionsManager::class.java.canonicalName
private const val MSG_MANAGER_NOT_ATTACHED = "PermissionsManager is not attached to activity!"

class PermissionsManager(
    private val activityContextProvider: ActivityContextProvider
) {

    private val activity: AppCompatActivity
        get() {
            return requireNotNull(
                activityContextProvider.activityContext as AppCompatActivity,
                ::MSG_MANAGER_NOT_ATTACHED
            )
        }

    fun requestPermissions(permissions: Array<String>, callback: Callback) {
        findPermissionsFragment().requestPermissions(permissions, callback)
    }

    fun isGranted(permission: String): Boolean {
        return activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    fun isGranted(permissions: Array<String>): Boolean {
        return permissions.all { isGranted(it) }
    }

    fun isRevoked(permission: String): Boolean {
        return activity.packageManager.isPermissionRevokedByPolicy(permission, activity.packageName)
    }

    fun isRevoked(permissions: Array<String>): Boolean {
        return permissions.all { isRevoked(it) }
    }

    private fun findPermissionsFragment(): PermissionsFragment {
        val fragmentManager = activity.supportFragmentManager

        return when (val permissionsFragment = findPermissionsFragment(fragmentManager)) {
            null -> createPermissionsFragment(fragmentManager)
            else -> permissionsFragment
        }
    }

    private fun createPermissionsFragment(fragmentManager: FragmentManager): PermissionsFragment {
        return PermissionsFragment().also { permissionsFragment ->
            fragmentManager
                .beginTransaction()
                .add(permissionsFragment, TAG)
                .commitAllowingStateLoss()
            fragmentManager.executePendingTransactions()
        }
    }

    private fun findPermissionsFragment(fragmentManager: FragmentManager): PermissionsFragment? {
        return fragmentManager.findFragmentByTag(TAG) as PermissionsFragment?
    }

    interface Callback {

        fun permissionsGranted(permissions: List<Permission>)

        fun permissionsDenied(permissions: List<Permission>)

        fun showRationale(permissions: List<Permission>)
    }
}
