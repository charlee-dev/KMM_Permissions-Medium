package com.adrianwitaszak.kmmpermissions.permissions

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
        BluetoothServicePermissionDelegate()
    }
    single<PermissionDelegate>(named(Permission.BLUETOOTH.name)) {
        BluetoothPermissionDelegate()
    }
    single<PermissionDelegate>(named(Permission.LOCATION_SERVICE_ON.name)) {
        LocationServicePermissionDelegate()
    }
    single<PermissionDelegate>(named(Permission.LOCATION_FOREGROUND.name)) {
        LocationForegroundPermissionDelegate()
    }
    single<PermissionDelegate>(named(Permission.LOCATION_BACKGROUND.name)) {
        LocationBackgroundPermissionDelegate(
            locationForegroundPermissionDelegate = get(named(Permission.LOCATION_FOREGROUND.name)),
        )
    }
}
