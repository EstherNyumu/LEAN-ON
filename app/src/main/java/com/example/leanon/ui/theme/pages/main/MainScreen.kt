package com.example.leanon.ui.theme.pages.main

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.leanon.data.AuthRepository
import com.example.leanon.models.BottomBarScreen
import com.example.leanon.navigation.AppNavHost
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.navigation.ROUTE_PROFILE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val bottomBarHeight = 56.dp
    val bottomBarOffsetHeightPx = remember { mutableStateOf(0f) }

    var buttonsVisible = remember { mutableStateOf(true) }
    LaunchedEffect(bottomBarOffsetHeightPx){
        buttonsVisible.value = bottomBarOffsetHeightPx.value >= -5
    }

    Scaffold(
        modifier = Modifier.bottomBarAnimatedScroll(
            height = bottomBarHeight, offsetHeightPx = bottomBarOffsetHeightPx
        ),
        bottomBar = { BottomBar(navController = navController, state = buttonsVisible,modifier = Modifier)},
        topBar = { TopAppBar(modifier = Modifier,navController = navController)}
    ){paddingValues->
        Box(modifier = Modifier.padding(paddingValues)){
            AppNavHost(navController = navController)
        }
    }

}
fun Modifier.bottomBarAnimatedScroll(
    height: Dp = 56.dp, offsetHeightPx: MutableState<Float>
): Modifier = composed {
    val bottomBarHeightPx = with(LocalDensity.current) {
        height.roundToPx().toFloat()
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = offsetHeightPx.value + delta
                offsetHeightPx.value = newOffset.coerceIn(-bottomBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    this.nestedScroll(nestedScrollConnection)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar( modifier:Modifier,navController: NavHostController) {
    var context = LocalContext.current
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = Color.White
        ) ,
        modifier = Modifier.background(
            Brush.horizontalGradient(
                colors = listOf(
                    Color(0xFFFF0078),
                    Color(0xFF9C27B0)
                )
            )
        ),
        title = {
            Text(
                text = "LEAN ON",
                fontSize = 30.sp,
                modifier = Modifier.padding(20.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace)


        },
        actions = {
            var authRepository = AuthRepository(navController,context)

//            IconButton(onClick = {
//                if(authRepository.isLoggedIn()){
//                    authRepository.logout()
//                }
//                else{
//                    navController.navigate(ROUTE_LOGIN)
//                }},
//                colors = IconButtonDefaults.iconButtonColors( contentColor = Color.White)
//            ) {
//                Icon(imageVector = Icons.Default.Logout, contentDescription = "logout")
//            }

            IconButton(onClick = {
                if(authRepository.isLoggedIn()){
                    navController.navigate(ROUTE_PROFILE)
                }
                else{
                    Toast.makeText(context, "You need to login first.", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUTE_LOGIN)
                }
            }, colors = IconButtonDefaults.iconButtonColors( contentColor = Color.White)
            ) {
                Icon(imageVector = Icons.Default.Person2, contentDescription = "profile")
            }



        }
    )

}

@Composable
fun BottomBar(navController: NavHostController,state:MutableState<Boolean>, modifier: Modifier = Modifier) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Bible,
        BottomBarScreen.Notepad,
        BottomBarScreen.Church
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    AnimatedVisibility(visible = state.value,
        enter = slideInVertically (initialOffsetY = {it}),
        exit = slideOutVertically (targetOffsetY = {it})) {
        NavigationBar(
            containerColor = Color.Transparent,
            modifier = Modifier.background(
                Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFFF0078),
                        Color(0xFF9C27B0)
                    )
                )
            )
        ) {
            screens.forEach{screen->
                AddItem(screen = screen,
                    currentDestination = currentDestination,
                    navController = navController )
            }
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
            Text(text = screen.title!!)
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id){
                }
                launchSingleTop = true
            }
        },
        colors = NavigationBarItemDefaults.colors(
            unselectedIconColor = Color.White,
            unselectedTextColor = Color.White,
            selectedTextColor = Color.Black,
            selectedIconColor = Color.Black
        ),
        icon = {
            Icon(
                imageVector = screen.icon!!,
                contentDescription = ""
            )
        },
    )
}