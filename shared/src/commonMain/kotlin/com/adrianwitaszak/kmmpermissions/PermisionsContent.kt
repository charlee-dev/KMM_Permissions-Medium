package com.adrianwitaszak.kmmpermissions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Suppress("FunctionName")
@Composable
internal fun PermissionsContent() {
    val scope = rememberCoroutineScope()
//    val permissionsService: PermissionsService by lazy {
//        sdkContext.get().get()
//    }
//    val requiredPermissions = listOf(
//        Permission.LOCATION_SERVICE_ON,
//        Permission.LOCATION_FOREGROUND,
//        Permission.LOCATION_BACKGROUND,
//        Permission.MOTION,
//        Permission.BLUETOOTH_SERVICE_ON,
//        Permission.BLUETOOTH,
//    )

    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Column {
                        Text(
                            text = "Permissions",
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.onSurface,
                        )
                        Divider()
                    }
                }
//        items(Permission.values()) { permission ->
//            val permissionState by permissionsService.checkPermissionFlow(permission)
//                .collectAsState(permissionsService.checkPermission(permission))
//            StateSection(
//                name = permission.name,
//                state = permissionState,
//                buttonShowing = permissionState.notGranted(),
//                isRequired = permission in requiredPermissions,
//                onRequestPermissionClick = {
//                    scope.launch {
//                        permissionsService.providePermission(permission)
//                    }
//                },
//                onOpenSettingsClick = {
//                    permissionsService.openSettingPage(permission)
//                }
//            )
//        }
            }
        }
    }
}

//@Composable
//private fun StateSection(
//    name: String,
//    state: PermissionState,
//    buttonText: String = "Request $name",
//    buttonShowing: Boolean = true,
//    isRequired: Boolean,
//    onRequestPermissionClick: () -> Unit,
//    onOpenSettingsClick: () -> Unit,
//) {
//    var required by remember { mutableStateOf(false) }
//    val colorState by animateColorAsState(
//        when (state) {
//            PermissionState.GRANTED -> Color.Green
//            PermissionState.NOT_DETERMINED -> Color.Gray
//            PermissionState.DENIED -> Color.Red
//        }
//    )
//
//    LaunchedEffect(isRequired) {
//        required = isRequired
//    }
//
//    Column(
//        verticalArrangement = Arrangement.spacedBy(4.dp)
//    ) {
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Column(
//                verticalArrangement = Arrangement.Top,
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(
//                    text = name,
//                    color = MaterialTheme.colors.onSurface,
//                    fontWeight = FontWeight.Bold,
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 1,
//                    softWrap = false,
//                    modifier = Modifier.background(MaterialTheme.colors.background)
//                )
//                AnimatedVisibility(required) {
//                    Text(
//                        text = "Required",
//                        color = MaterialTheme.colors.onSurface,
//                        style = MaterialTheme.typography.caption,
//                        fontWeight = FontWeight.Light,
//                        fontStyle = FontStyle.Italic,
//                        modifier = Modifier.padding(top = 4.dp)
//                    )
//                }
//            }
//            Icon(
//                imageVector = when (state) {
//                    PermissionState.GRANTED -> Icons.Default.Check
//                    PermissionState.NOT_DETERMINED -> Icons.Outlined.QuestionMark
//                    PermissionState.DENIED -> Icons.Outlined.Close
//                },
//                tint = colorState,
//                contentDescription = null,
//                modifier = Modifier.padding(horizontal = 8.dp)
//            )
//            MoboxButton(
//                text = "Settings",
//                onClick = onOpenSettingsClick,
//                modifier = Modifier.width(100.dp)
//            )
//        }
//        AnimatedVisibility(buttonShowing) {
//            MoboxButton(
//                text = buttonText,
//                onClick = onRequestPermissionClick,
//            )
//        }
//    }
//}
