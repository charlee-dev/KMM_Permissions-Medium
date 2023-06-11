package com.adrianwitaszak.kmmpermissions.android

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.adrianwitaszak.kmmpermissions.initKoin
import com.adrianwitaszak.kmmpermissions.ui.PermissionsContentAndroid
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val koinApplication = initKoin(
            additionalModules = listOf(
                module {
                    single<Context> { applicationContext }
                    single<Activity> { this@MainActivity }
                }
            )
        )

        setContent {
            PermissionsContentAndroid(
               koin = koinApplication.koin,
            )
        }
    }
}
