package com.adrianwitaszak.kmmpermissions.permissions

import com.adrianwitaszak.kmmpermissions.permissions.service.PermissionsService
import com.adrianwitaszak.kmmpermissions.permissions.service.PermissionsServiceImpl
import org.koin.core.module.Module
import org.koin.dsl.module

internal expect fun platformModule(): Module

val permissionsModule: Module = module {
    includes(platformModule())

    single<PermissionsService> {
        PermissionsServiceImpl()
    }
}
