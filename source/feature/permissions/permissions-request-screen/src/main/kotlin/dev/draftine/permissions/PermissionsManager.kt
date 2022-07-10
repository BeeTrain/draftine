package dev.draftine.permissions

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dev.draftine.permissions.presentation.view.PermissionsFragment
import java.lang.ref.WeakReference

private val TAG = PermissionsManager::class.java.canonicalName
private const val MSG_MANAGER_NOT_ATTACHED = "PermissionsManager is not attached to activity!"

class PermissionsManager {

    private var activityRef: WeakReference<AppCompatActivity?>? = null

    private val activity: AppCompatActivity
        get() = requireNotNull(activityRef?.get(), ::MSG_MANAGER_NOT_ATTACHED)

    infix fun attachTo(activity: AppCompatActivity) {
        activityRef = WeakReference(activity)
    }

    fun requestPermissions(permissions: Array<String>, callback: PermissionsFragment.Callback) {
        findPermissionsFragment().requestPermissions(permissions, callback)
    }

    fun isGranted(permission: String): Boolean {
        return activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    fun isRevoked(permission: String): Boolean {
        return activity.packageManager.isPermissionRevokedByPolicy(permission, activity.packageName)
    }

    private fun findPermissionsFragment(): PermissionsFragment {
        val fragmentManager = requireNotNull(activityRef?.get()?.supportFragmentManager, ::MSG_MANAGER_NOT_ATTACHED)

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
}
