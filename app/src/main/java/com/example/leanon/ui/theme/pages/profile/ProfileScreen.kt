package com.example.leanon.ui.theme.pages.profile

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.data.AuthRepository
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink

@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        var context = LocalContext.current
        Text(
            text = "My Profile",
            fontSize = 30.sp,
            color = PrimePink,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Text(text = "Name: ", fontFamily = FontFamily.Monospace)
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Email: ",fontFamily = FontFamily.Monospace)
        Spacer(modifier = Modifier.height(20.dp))

        ClickableText(
            text = AnnotatedString("Change Password"),
            onClick = {/*how to change pass*/},
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                color = PrimePink
            ))
        Spacer(modifier = Modifier.height(20.dp))

        ClickableText(
            text = AnnotatedString("Need any help?"),
            onClick = {/*contact customer care*/},
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                color = PrimePink
            ))
        Spacer(modifier = Modifier.height(20.dp))

        ClickableText(
            text = AnnotatedString("Log out"),
            onClick = {
                var authRepository = AuthRepository(navController, context)
                authRepository.logout()
                navController.navigate(ROUTE_LOGIN)
            },
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                color = PrimePink
            )
        )

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ProfileScreenPreview() {
    LeanOnTheme {
        ProfileScreen(rememberNavController())
    }
}