package com.adrianwitaszak.kmmpermissions.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.adrianwitaszak.kmmpermissions.ui.PermissionsContentAndroid

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PermissionsContentAndroid()
        }
    }
}
