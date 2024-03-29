package com.pandorina.hal_fiyatlar.presentation.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.pandorina.hal_fiyatlar.R

sealed class MenuAction(
    @StringRes val label: Int,
    val icon: ImageVector
) {
    object Share : MenuAction(R.string.share, Icons.Default.Share)
    object Back : MenuAction(R.string.back, Icons.Default.ArrowBack)
    object Clear: MenuAction(R.string.back, Icons.Filled.DeleteSweep)
    object Search: MenuAction(R.string.search, Icons.Filled.Search)
}