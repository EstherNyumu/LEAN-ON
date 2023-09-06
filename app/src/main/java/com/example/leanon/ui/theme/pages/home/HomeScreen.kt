package com.example.leanon.ui.theme.pages.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.data.AuthRepository
import com.example.leanon.navigation.ROUTE_ADD_POST
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink


@Composable
fun HomeScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        Spacer(modifier = Modifier.weight(1f))
        FloatingActionButton(onClick = {
            var authRepository = AuthRepository(navController,context)
            if(!(authRepository.isLoggedIn())){
                navController.navigate(ROUTE_LOGIN)
            }
            else{
                navController.navigate(ROUTE_ADD_POST)
            }
        },
            containerColor = PrimePink,
            contentColor = Color.White,
            shape = CircleShape
        ){
            Icon(Icons.Filled.Add, contentDescription ="Add")
        }
    }
}




@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreview() {
    LeanOnTheme {
        HomeScreen(rememberNavController())
    }
}

