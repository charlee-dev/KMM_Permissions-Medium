package com.adrianwitaszak.kmmpermissions.permissions.service

import com.adrianwitaszak.kmmpermissions.permissions.model.Permission
import com.adrianwitaszak.kmmpermissions.permissions.model.PermissionState
import kotlinx.coroutines.flow.Flow

interface PermissionsService {
    fun checkPermission(permission: Permission): PermissionState
    fun checkPermissionFlow(permission: Permission): Flow<PermissionState>
    suspend fun providePermission(permission: Permission)
    fun openSettingPage(permission: Permission)

    companion object {
        const val PERMISSION_CHECK_FLOW_FREQUENCY = 1000L
    }
}
