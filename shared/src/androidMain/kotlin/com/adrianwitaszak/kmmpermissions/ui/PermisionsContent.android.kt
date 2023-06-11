package com.adrianwitaszak.kmmpermissions.ui

import androidx.compose.runtime.Composable
import com.adrianwitaszak.kmmpermissions.PermissionsContent
import com.adrianwitaszak.kmmpermissions.permissions.service.PermissionsService
import org.koin.core.Koin

@Suppress("FunctionName")
@Composable
fun PermissionsContentAndroid(koin: Koin) {
    PermissionsContent(
        permissionsService = koin.get<PermissionsService>()
    )
}
