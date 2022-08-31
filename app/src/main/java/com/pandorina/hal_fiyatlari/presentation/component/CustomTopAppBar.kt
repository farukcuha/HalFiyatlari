package com.pandorina.hal_fiyatlari.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.pandorina.hal_fiyatlari.R

@Composable
fun CustomTopAppBar(
    title: String,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = { Text(
            text = title,
            color = Color.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        ) },
        backgroundColor = Color.White,
        navigationIcon = navigationIcon,
        actions = actions
    )
}

@Composable
fun MenuIcon(
    action: MenuAction,
    onClickMenu: () -> Unit
) {
    IconButton(onClick = { onClickMenu() }) {
        Icon(action.icon, contentDescription = stringResource(id = action.label))
    }
}

@Composable
@Preview
fun Preview() {
    CustomTopAppBar(
        title = stringResource(id = R.string.home),
        navigationIcon = {
            MenuIcon(MenuAction.Back) {
                Log.d("action", "clicked_back")
            }
        },
        actions = {
            MenuIcon(MenuAction.Info) {
                Log.d("action", "clicked_info")
            }
            MenuIcon(MenuAction.Settings) {
                Log.d("action", "clicked_settings")
            }
        }
    )
}