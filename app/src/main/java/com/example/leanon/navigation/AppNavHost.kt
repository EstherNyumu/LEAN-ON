package com.example.leanon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.leanon.models.Destinations
import com.example.leanon.ui.theme.pages.notepad.BibleStudyNotepadScreen
import com.example.leanon.ui.theme.pages.notepad.PrayerNotepadScreen
import com.example.leanon.ui.theme.pages.notepad.SermonNotepadScreen


@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController = rememberNavController(),
               startDestination: String = ROUTE_HOME) {
    NavHost(modifier = modifier,
        navController = navController,
        startDestination = startDestination){
        composable(ROUTE_HOME){
            Destinations.HomeScreen
        }
        composable(ROUTE_BIBLE){
            Destinations.BibleScreen
        }
//        composable(ROUTE_BOOKS){
//            BooksScreen(navController)
//        }
        composable(ROUTE_CHURCH){
            Destinations.ChurchScreen
        }
        composable(ROUTE_NOTEPAD){
            Destinations.NotepadScreen
        }
        composable(ROUTE_PRAYER_NOTEPAD){
            PrayerNotepadScreen()
        }
        composable(ROUTE_SERMON_NOTEPAD){
            SermonNotepadScreen()
        }
        composable(ROUTE_BIBLE_STUDY_NOTEPAD){
            BibleStudyNotepadScreen()
        }
//        composable(ROUTE_LOGIN){
//            LoginScreen()
//        }
//        composable(ROUTE_SIGNUP){
//            SignUpScreen()
//        }


    }
}