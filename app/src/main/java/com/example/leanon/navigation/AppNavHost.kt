package com.example.leanon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.leanon.ui.theme.pages.bible.BibleScreen
import com.example.leanon.ui.theme.pages.church.ChurchScreen
import com.example.leanon.ui.theme.pages.home.AddPostsScreen
import com.example.leanon.ui.theme.pages.home.HomeScreen
import com.example.leanon.ui.theme.pages.notepad.AddBibleStudyScreen
import com.example.leanon.ui.theme.pages.notepad.AddPrayerScreen
import com.example.leanon.ui.theme.pages.notepad.AddSermonScreen
import com.example.leanon.ui.theme.pages.notepad.BibleStudyNotepadScreen
import com.example.leanon.ui.theme.pages.notepad.NotepadScreen
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
            HomeScreen(navController)
        }
        composable(ROUTE_BIBLE){
           BibleScreen()
        }
//        composable(ROUTE_BOOKS){
//            BooksScreen(navController)
//        }
        composable(ROUTE_CHURCH){
            ChurchScreen()
        }
        composable(ROUTE_NOTEPAD){
            NotepadScreen(navController)
        }
        composable(ROUTE_PRAYER_NOTEPAD){
            PrayerNotepadScreen(navController)
        }
        composable(ROUTE_SERMON_NOTEPAD){
            SermonNotepadScreen(navController)
        }
        composable(ROUTE_BIBLE_STUDY_NOTEPAD){
            BibleStudyNotepadScreen(navController)
        }
        composable(ROUTE_ADD_PRAYER){
            AddPrayerScreen(navController)
        }
        composable(ROUTE_ADD_SERMON){
            AddSermonScreen(navController)
        }
        composable(ROUTE_ADD_BIBLE_STUDY){
            AddBibleStudyScreen(navController)
        }
        composable(ROUTE_ADD_POST){
            AddPostsScreen(navController)
        }
//        composable(ROUTE_LOGIN){
//            LoginScreen()
//        }
//        composable(ROUTE_SIGNUP){
//            SignUpScreen()
//        }


    }
}