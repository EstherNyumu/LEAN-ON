package com.example.leanon.ui.theme.pages.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.leanon.models.BottomBarScreen
import com.example.leanon.navigation.AppNavHost
import com.example.leanon.ui.theme.PrimePink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController)},
//        topBar = { TopAppBar(modifier = Modifier)}
    ){paddingValues->
        AppNavHost(navController = navController)
        val modifier = Modifier.padding(paddingValues)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar( modifier:Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
      Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = PrimePink,
            titleContentColor = Color.White
        ) ,
        title = {
        Text(
            text = "LEAN ON",
            fontSize = 30.sp,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace)
    }, scrollBehavior = scrollBehavior)

}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Bible,
        BottomBarScreen.Notepad,
        BottomBarScreen.Church
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(containerColor = PrimePink) {
        screens.forEach{screen->
            AddItem(screen = screen,
                currentDestination = currentDestination,
                navController = navController )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = {
            Text(text = screen.title)
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
                  },
        colors = NavigationBarItemDefaults.colors(
            unselectedIconColor = Color.LightGray,
            unselectedTextColor = Color.LightGray,
            selectedTextColor = Color.White,
            selectedIconColor = Color.White
        ),
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = ""
            )
        },
    )
}