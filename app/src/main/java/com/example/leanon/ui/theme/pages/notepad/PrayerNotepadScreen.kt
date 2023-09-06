package com.example.leanon.ui.theme.pages.notepad

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.navigation.ROUTE_ADD_PRAYER
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink

@Composable
fun PrayerNotepadScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Text(text = "My pleads to God")
            Spacer(modifier = Modifier.weight(1f))
            ExtendedFloatingActionButton(
                onClick = {
                    navController.navigate(ROUTE_ADD_PRAYER)
                },
                containerColor = PrimePink,
                contentColor = Color.White,
                icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
                text = { Text(text = "Add Prayer")}
            )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PrayerNotepadScreenPreview() {
    LeanOnTheme {
        PrayerNotepadScreen(rememberNavController())
    }
}