package com.pandorina.hal_fiyatlari.presentation.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.pandorina.hal_fiyatlari.R

sealed class MenuAction(
    @StringRes val label: Int,
    val icon: ImageVector
) {
    object Settings : MenuAction(R.string.settings, Icons.Default.Settings)
    object Info : MenuAction(R.string.info, Icons.Default.Info)
    object Back : MenuAction(R.string.back, Icons.Default.ArrowBack)
    object Cancel : MenuAction(R.string.cancel, Icons.Default.Close)
}