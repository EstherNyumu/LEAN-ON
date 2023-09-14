package com.example.leanon.ui.theme.pages.bible

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.ui.theme.LeanOnTheme

@Composable
fun NewTestamentScreen(navController: NavHostController) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally){

    }
}

@Preview
@Composable
fun NewTestamentScreenPreview() {
    LeanOnTheme {
        NewTestamentScreen(rememberNavController())
    }
}