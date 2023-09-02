package com.example.leanon.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Church
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Padding
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
){
    object HomeScreen : Destinations(
        route = "home_screen",
        title = "Home",
        icon = Icons.Outlined.Home
    )
    object BibleScreen : Destinations(
        route = "bible_screen",
        title = "Bible",
        icon = Icons.Outlined.Book
    )
    object NotepadScreen : Destinations(
        route = "notepad_screen",
        title = "Notepad",
        icon = Icons.Outlined.Padding
    )
    object ChurchScreen : Destinations(
        route = "church_screen",
        title = "Church",
        icon = Icons.Outlined.Church
    )
}

