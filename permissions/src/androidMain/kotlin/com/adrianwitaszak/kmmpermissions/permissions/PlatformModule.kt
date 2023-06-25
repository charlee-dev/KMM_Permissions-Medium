package com.adrianwitaszak.kmmpermissions.permissions

import android.bluetooth.BluetoothManager
import android.content.Context
import com.adrianwitaszak.kmmpermissions.permissions.delegate.BluetoothPermissionDelegate
import com.adrianwitaszak.kmmpermissions.permissions.delegate.BluetoothServicePermissionDelegate
import com.adrianwitaszak.kmmpermissions.permissions.delegate.LocationBackgroundPermissionDelegate
import com.adrianwitaszak.kmmpermissions.permissions.delegate.LocationForegroundPermissionDelegate
import com.adrianwitaszak.kmmpermissions.permissions.delegate.LocationServicePermissionDelegate
import com.adrianwitaszak.kmmpermissions.permissions.delegate.PermissionDelegate
import com.adrianwitaszak.kmmpermissions.permissions.model.Permission
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal actual fun platformModule(): Module = module {
    single<PermissionDelegate>(named(Permission.BLUETOOTH_SERVICE_ON.name)) {
        BluetoothServicePermissionDelegate(
            context = get(),
            bluetoothAdapter = get(),
        )
    }
    single<PermissionDelegate>(named(Permission.BLUETOOTH.name)) {
        BluetoothPermissionDelegate(
            context = get(),
            activity = inject(),
        )
    }
    single {
        get<Context>().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    }
    single {
        get<BluetoothManager>().adapter
    }
    single<PermissionDelegate>(named(Permission.LOCATION_SERVICE_ON.name)) {
        LocationServicePermissionDelegate(
            context = get(),
            locationManager = get(),
        )
    }
    single<PermissionDelegate>(named(Permission.LOCATION_FOREGROUND.name)) {
        LocationForegroundPermissionDelegate(
            context = get(),
            activity = inject(),
        )
    }
    single<PermissionDelegate>(named(Permission.LOCATION_BACKGROUND.name)) {
        LocationBackgroundPermissionDelegate(
            context = get(),
            activity = inject(),
            locationForegroundPermissionDelegate = get(named(Permission.LOCATION_FOREGROUND.name)),
        )
    }
}
