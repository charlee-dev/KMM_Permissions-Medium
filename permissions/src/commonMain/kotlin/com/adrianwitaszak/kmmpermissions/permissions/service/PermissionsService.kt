package com.adrianwitaszak.kmmpermissions.permissions.service

import com.adrianwitaszak.kmmpermissions.permissions.model.Permission
import com.adrianwitaszak.kmmpermissions.permissions.model.PermissionState
import kotlinx.coroutines.flow.Flow

/**
 * The PermissionsService interface provides methods for managing permissions within an application.
 */
interface PermissionsService {
    /**
     * Checks the current state of a specified permission.
     *
     * @param permission The permission to check.
     * @return The current state of the permission.
     */
    fun checkPermission(permission: Permission): PermissionState

    /**
     * Returns a Flow that emits the PermissionState whenever it changes for the specified permission.
     * The emission frequency is determined by the PERMISSION_CHECK_FLOW_FREQUENCY constant.
     *
     * @param permission The permission to monitor.
     * @return A flow of permission states for the specified permission.
     */
    fun checkPermissionFlow(permission: Permission): Flow<PermissionState>

    /**
     * Requests the application to provide the specified permission.
     *
     * @param permission The permission to be provided.
     */
    suspend fun providePermission(permission: Permission)

    /**
     * Opens the settings page for the specified permission within the application or the device's settings app.
     *
     * @param permission The permission for which to open the settings page.
     */
    fun openSettingPage(permission: Permission)

    /**
     * Companion object that holds additional constants and properties related to the PermissionsService interface.
     */
    companion object {
        /**
         * The frequency (in milliseconds) at which the checkPermissionFlow method emits the permission state updates.
         * By default, it is set to 1000L (1 second).
         * You can adjust this value as per your requirements.
         */
        const val PERMISSION_CHECK_FLOW_FREQUENCY = 1000L
    }
}
