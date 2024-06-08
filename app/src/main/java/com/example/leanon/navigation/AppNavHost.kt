package com.example.leanon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.leanon.models.BottomBarScreen
import com.example.leanon.ui.theme.pages.bible.BibleScreen
import com.example.leanon.ui.theme.pages.church.ChurchScreen
import com.example.leanon.ui.theme.pages.home.AddPostsScreen
import com.example.leanon.ui.theme.pages.home.HomeScreen
import com.example.leanon.ui.theme.pages.login.LoginScreen
import com.example.leanon.ui.theme.pages.notepad.AddBibleStudyScreen
import com.example.leanon.ui.theme.pages.notepad.AddPrayerScreen
import com.example.leanon.ui.theme.pages.notepad.AddSermonScreen
import com.example.leanon.ui.theme.pages.notepad.BibleStudyNotepadScreen
import com.example.leanon.ui.theme.pages.notepad.NotepadScreen
import com.example.leanon.ui.theme.pages.notepad.PrayerNotepadScreen
import com.example.leanon.ui.theme.pages.notepad.SermonNotepadScreen
import com.example.leanon.ui.theme.pages.profile.ProfileScreen
import com.example.leanon.ui.theme.pages.signup.SignUpScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = BottomBarScreen.Home.route){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Bible.route){
            BibleScreen(navController)
        }
        composable(route = BottomBarScreen.Notepad.route){
            NotepadScreen(navController)
        }
        composable(route = BottomBarScreen.Church.route){
            ChurchScreen()
        }
        composable(route = ROUTE_ADD_POST){
            AddPostsScreen(navController)
        }
        composable(route = ROUTE_PRAYER_NOTEPAD){
            PrayerNotepadScreen(navController)
        }
        composable(route = ROUTE_SERMON_NOTEPAD){
            SermonNotepadScreen(navController)
        }
        composable(route = ROUTE_BIBLE_STUDY_NOTEPAD){
            BibleStudyNotepadScreen(navController)
        }
        composable(route = ROUTE_ADD_PRAYER){
            AddPrayerScreen(navController)
        }
        composable(route = ROUTE_ADD_SERMON){
            AddSermonScreen(navController)
        }
        composable(route = ROUTE_ADD_BIBLE_STUDY){
            AddBibleStudyScreen(navController)
        }
        composable(route = ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(route = ROUTE_SIGNUP){
            SignUpScreen(navController)
        }
        composable(route = ROUTE_PROFILE){
            ProfileScreen(navController)
        }
    }
}