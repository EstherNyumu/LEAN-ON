package com.example.leanon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.leanon.models.BottomBarScreen
import com.example.leanon.ui.theme.pages.bible.BibleScreen
import com.example.leanon.ui.theme.pages.church.ChurchScreen
import com.example.leanon.ui.theme.pages.home.HomeScreen
import com.example.leanon.ui.theme.pages.notepad.NotepadScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = BottomBarScreen.Home.route){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Bible.route){
            BibleScreen()
        }
        composable(route = BottomBarScreen.Notepad.route){
            NotepadScreen(navController)
        }
        composable(route = BottomBarScreen.Church.route){
            ChurchScreen()
        }
    }
}