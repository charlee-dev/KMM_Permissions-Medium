package com.adrianwitaszak.kmmpermissions

import androidx.compose.ui.window.ComposeUIViewController
import com.adrianwitaszak.kmmpermissions.permissions.service.PermissionsService
import platform.UIKit.UIViewController

@Suppress("FunctionName", "unused")
fun MainViewController(): UIViewController {
    val koin = initKoin().koin

    return ComposeUIViewController {
        PermissionsContent(
            permissionsService = koin.get<PermissionsService>()
        )
    }
}
