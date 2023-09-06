package com.example.leanon.ui.theme.pages.profile

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.ui.theme.LeanOnTheme

@Composable
fun ChangePassScreen(navController: NavHostController) {

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ChangePassScreenPreview() {
    LeanOnTheme {
        ChangePassScreen(rememberNavController())
    }
}