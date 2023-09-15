package com.example.leanon.ui.theme.pages.notepad

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.data.AuthRepository
import com.example.leanon.navigation.ROUTE_BIBLE_STUDY_NOTEPAD
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.navigation.ROUTE_PRAYER_NOTEPAD
import com.example.leanon.navigation.ROUTE_SERMON_NOTEPAD
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink

@Composable
fun NotepadScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var context = LocalContext.current
        IconButton(onClick = { /*TODO*/ },
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White,
                contentColor = PrimePink)) {
            Icon(Icons.Outlined.Padding,
                contentDescription ="",
                modifier = Modifier
                    .height(500.dp)
                    .width(500.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "I want to note down: ",
            color = Color.DarkGray,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                var authRepository = AuthRepository(navController,context)
                if(!(authRepository.isLoggedIn())){
                    navController.navigate(ROUTE_LOGIN)
                }
                else{
                    navController.navigate(ROUTE_PRAYER_NOTEPAD)
                }


            },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, color = PrimePink)
        ) {
            Text(text = "A prayer", color = PrimePink)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                var authRepository = AuthRepository(navController,context)
                if(!(authRepository.isLoggedIn())){
                    navController.navigate(ROUTE_LOGIN)
                }
                else{
                    navController.navigate(ROUTE_SERMON_NOTEPAD)
                }

            },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, color = PrimePink)) {
            Text(text = "Today's Sermon",color = PrimePink)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                var authRepository = AuthRepository(navController,context)
                if(!(authRepository.isLoggedIn())){
                    navController.navigate(ROUTE_LOGIN)
                }
                else{
                    navController.navigate(ROUTE_BIBLE_STUDY_NOTEPAD)
                }

            },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, color = PrimePink)) {
            Text(text = "A Bible Study Session", color = PrimePink)
        }
    }
}

@Preview
@Composable
fun NotepadScreenPreview() {
    LeanOnTheme {
        NotepadScreen(rememberNavController())
    }
}