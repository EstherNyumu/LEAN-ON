package com.example.leanon.ui.theme.pages.bible

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.ui.theme.LeanOnTheme

@Composable
fun OldTestamentScreen(navController: NavHostController) {

}

@Preview
@Composable
fun OldTestamentScreenPreview() {
    LeanOnTheme {
        OldTestamentScreen(rememberNavController())
    }
}