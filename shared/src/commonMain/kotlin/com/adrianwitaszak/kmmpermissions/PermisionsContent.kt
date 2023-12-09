package com.adrianwitaszak.kmmpermissions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.adrianwitaszak.kmmpermissions.permissions.model.Permission
import com.adrianwitaszak.kmmpermissions.permissions.model.PermissionState
import com.adrianwitaszak.kmmpermissions.permissions.service.PermissionsService
import kotlinx.coroutines.launch

@Suppress("FunctionName")
@Composable
internal fun PermissionsContent(
    permissionsService: PermissionsService,
) {
    val scope = rememberCoroutineScope()

    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(
                    top = 64.dp,
                    bottom = 64.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Column {
                        Text(
                            text = "Permissions",
                            style = MaterialTheme.typography.h4,
                            color = MaterialTheme.colors.onSurface,
                        )
                        Divider()
                    }
                }
                items(Permission.values()) { permission ->
                    val permissionState by permissionsService.checkPermissionFlow(permission)
                        .collectAsState(permissionsService.checkPermission(permission))
                    PermissionItem(
                        permissionName = permission.name,
                        permissionState = permissionState,
                        onRequestClick = {
                            scope.launch {
                                permissionsService.providePermission(permission)
                            }
                        },
                        onOpenSettingsClick = {
                            permissionsService.openSettingPage(permission)
                        },
                    )
                }
            }
        }
    }
}

@Suppress("FunctionName")
@Composable
internal fun PermissionItem(
    permissionName: String,
    permissionState: PermissionState,
    onRequestClick: () -> Unit,
    onOpenSettingsClick: () -> Unit,
) {
    val colorState by animateColorAsState(
        when (permissionState) {
            PermissionState.GRANTED -> Color.Green
            PermissionState.NOT_DETERMINED -> Color.Gray
            PermissionState.DENIED -> Color.Red
        }
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = permissionName,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = when (permissionState) {
                    PermissionState.GRANTED -> Icons.Default.Check
                    PermissionState.NOT_DETERMINED -> Icons.Outlined.QuestionMark
                    PermissionState.DENIED -> Icons.Outlined.Close
                },
                tint = colorState,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Button(
                onClick = onOpenSettingsClick,
            ) {
                Text(
                    text = "Settings",
                    color = MaterialTheme.colors.onPrimary,
                )
            }
        }
        AnimatedVisibility(permissionState.notGranted()) {
            Button(
                onClick = onRequestClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Request",
                    color = MaterialTheme.colors.onPrimary,
                )
            }
        }
    }
}
