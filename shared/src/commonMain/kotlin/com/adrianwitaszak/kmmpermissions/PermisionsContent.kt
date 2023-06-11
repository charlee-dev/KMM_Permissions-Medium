package com.adrianwitaszak.kmmpermissions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Suppress("FunctionName")
@Composable
internal fun PermissionsContent() {
    val scope = rememberCoroutineScope()

    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
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
                items(items = listOf("LOCATION")) { permission ->
                    PermissionItem(
                        permissionName = permission, // Permission name
                        isGranted = false, // Is permission granted?
                        onRequestClick = {
                            scope.launch {
                                // Request permission
                            }
                        },
                        onOpenSettingsClick = {
                            // Open settings
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
    isGranted: Boolean,
    onRequestClick: () -> Unit,
    onOpenSettingsClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = permissionName,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = onOpenSettingsClick,
            ) {
                Text(
                    text = "Request",
                    color = MaterialTheme.colors.onPrimary,
                )
            }
        }
        AnimatedVisibility(!isGranted) {
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
