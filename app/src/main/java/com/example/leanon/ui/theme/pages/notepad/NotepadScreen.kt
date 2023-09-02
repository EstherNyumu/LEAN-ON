package com.example.leanon.ui.theme.pages.notepad

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.navigation.ROUTE_BIBLE_STUDY_NOTEPAD
import com.example.leanon.navigation.ROUTE_PRAYER_NOTEPAD
import com.example.leanon.navigation.ROUTE_SERMON_NOTEPAD
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink

@Composable
fun NotepadScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(PrimePink)
        ) {
            Text(
                text = "LEAN ON",
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier.padding(20.dp),
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(20.dp))
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
        Text(text = "I want to write: ")
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                      navController.navigate(ROUTE_PRAYER_NOTEPAD)
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
                      navController.navigate(ROUTE_SERMON_NOTEPAD)
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, color = PrimePink)) {
            Text(text = "Today's Sermon",color = PrimePink)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                      navController.navigate(ROUTE_BIBLE_STUDY_NOTEPAD)
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, color = PrimePink)) {
            Text(text = "Your Bible Study", color = PrimePink)
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun NotepadScreenPreview() {
    LeanOnTheme {
        NotepadScreen(rememberNavController())
    }
}