package com.example.leanon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.leanon.models.Destinations
import com.example.leanon.ui.theme.pages.bible.BibleScreen
import com.example.leanon.ui.theme.pages.church.ChurchScreen
import com.example.leanon.ui.theme.pages.home.HomeScreen
import com.example.leanon.ui.theme.pages.notepad.NotepadScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.HomeScreen.route) {
        composable(Destinations.HomeScreen.route) {
            HomeScreen()
        }
        composable(Destinations.BibleScreen.route) {
           BibleScreen()
        }
        composable(Destinations.NotepadScreen.route) {
            NotepadScreen(navController)
        }
        composable(Destinations.ChurchScreen.route) {
            ChurchScreen()
        }
    }
}