package com.adrianwitaszak.kmmpermissions.permissions.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.app.ActivityCompat
import com.adrianwitaszak.kmmpermissions.permissions.model.Permission
import com.adrianwitaszak.kmmpermissions.permissions.model.PermissionState

internal fun Context.openPage(
    action: String,
    newData: Uri? = null,
    onError: (Exception) -> Unit,
) {
    try {
        val intent = Intent(action).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            newData?.let { data = it }
        }
        startActivity(intent)
    } catch (e: Exception) {
        onError(e)
    }
}

internal fun checkPermissions(
    context: Context,
    activity: Lazy<Activity>,
    permissions: List<String>,
): PermissionState {
    permissions.ifEmpty { return PermissionState.GRANTED } // no permissions needed
    val status: List<Int> = permissions.map {
        context.checkSelfPermission(it)
    }
    val isAllGranted: Boolean = status.all { it == PackageManager.PERMISSION_GRANTED }
    if (isAllGranted) return PermissionState.GRANTED

    // SDK starts checking permissions before activity is available, so until we have activity
    // we can't check permission rationale, so we return NOT_DETERMINED. It makes not difference
    // for AppState, because permission is not granted anyway. Code below matters only in the UI,
    // but the Activity is ready at this point.
    val isAllRequestRationale: Boolean = try {
        permissions.all {
            !activity.value.shouldShowRequestPermissionRationale(it)
        }
    } catch (t: Throwable) {
        t.printStackTrace()
        true
    }
    return if (isAllRequestRationale) PermissionState.NOT_DETERMINED
    else PermissionState.DENIED
}

internal fun Activity.providePermissions(
    permissions: List<String>,
    onError: (Throwable) -> Unit,
) {
    try {
        ActivityCompat.requestPermissions(
            this, permissions.toTypedArray(), 100
        )
    } catch (t: Throwable) {
        onError(t)
    }
}

internal fun Context.openAppSettingsPage(permission: Permission) {
    openPage(
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        newData = Uri.parse("package:$packageName"),
        onError = { throw CannotOpenSettingsException(permission.name) }
    )
}
