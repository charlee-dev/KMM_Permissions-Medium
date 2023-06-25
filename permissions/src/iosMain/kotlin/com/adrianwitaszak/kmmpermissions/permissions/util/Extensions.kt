package com.adrianwitaszak.kmmpermissions.permissions.util

import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

fun openNSUrl(string: String) {
    val settingsUrl: NSURL = NSURL.URLWithString(string)!!
    if (UIApplication.sharedApplication.canOpenURL(settingsUrl)) {
        UIApplication.sharedApplication.openURL(settingsUrl)
    } else throw CannotOpenSettingsException(string)
}

internal fun openAppSettingsPage() {
    openNSUrl(UIApplicationOpenSettingsURLString)
}
