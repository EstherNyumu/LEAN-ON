package com.example.leanon.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Padding
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home

    )
    object Bible: BottomBarScreen(
        route = "bible",
        title = "Bible",
        icon = Icons.Default.Book

    )
    object Notepad: BottomBarScreen(
        route = "notepad",
        title = "Notepad",
        icon = Icons.Default.Padding

    )
    object Church: BottomBarScreen(
        route = "church",
        title = "Church",
        icon = Icons.Default.Church

    )
}
