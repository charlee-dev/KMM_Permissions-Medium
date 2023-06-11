package com.adrianwitaszak.kmmpermissions

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

@Suppress("FunctionName", "unused")
fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        PermissionsContent()
    }
}
