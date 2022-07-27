package dev.draftine.utils.permissions

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import dev.draftine.utils.permissions.model.Permission

private const val PERMISSIONS_REQUEST_CODE = 1000

class PermissionsFragment : Fragment() {

    private var callback: PermissionsManager.Callback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    @TargetApi(Build.VERSION_CODES.M)
    internal fun requestPermissions(permissions: Array<String>, callback: PermissionsManager.Callback) {
        requestPermissions(permissions, PERMISSIONS_REQUEST_CODE)
        this.callback = callback
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != PERMISSIONS_REQUEST_CODE) return

        val shouldShowRequestPermissionRationale = BooleanArray(permissions.size)

        for (i in permissions.indices) {
            shouldShowRequestPermissionRationale[i] = shouldShowRequestPermissionRationale(permissions[i])
        }

        onRequestPermissionsResult(permissions, grantResults, shouldShowRequestPermissionRationale)
    }

    private fun onRequestPermissionsResult(
        permissions: Array<String>,
        grantResults: IntArray,
        shouldShowRequestPermissionRationale: BooleanArray
    ) {
        var permissionsGranted = true
        var showRationale = false
        val permissionsList = ArrayList<Permission>(permissions.size)
        for (i in permissions.indices) {
            val granted = grantResults[i] == PackageManager.PERMISSION_GRANTED
            permissionsGranted = permissionsGranted && granted
            if (showRationale.not() && granted.not() && shouldShowRequestPermissionRationale[i]) {
                showRationale = true
            }
            permissionsList.add(Permission(permissions[i], granted, shouldShowRequestPermissionRationale[i]))
        }

        callback?.let {
            when {
                permissionsGranted -> it.permissionsGranted(permissionsList)
                showRationale -> it.showRationale(permissionsList)
                else -> it.permissionsDenied(permissionsList) // never ask again
            }
        }
    }
}
