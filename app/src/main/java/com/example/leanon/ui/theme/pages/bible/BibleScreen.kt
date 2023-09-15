package com.example.leanon.ui.theme.pages.bible

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.navigation.ROUTE_NEW_TESTAMENT
import com.example.leanon.navigation.ROUTE_OLD_TESTAMENT
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibleScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        IconButton(onClick = { /*TODO*/ },
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White,
                contentColor = PrimePink
            )) {
            Icon(
                Icons.Outlined.Book,
                contentDescription ="Book Icon",
                modifier = Modifier
                    .height(500.dp)
                    .width(500.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "I want to read: ",
            color = Color.DarkGray,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                      navController.navigate(ROUTE_OLD_TESTAMENT)
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, color = PrimePink)
        ) {
            Text(text = "The Old Testament", color = PrimePink)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                      navController.navigate(ROUTE_NEW_TESTAMENT)
            },
            colors = ButtonDefaults.buttonColors(Color.White),
            shape = RoundedCornerShape(20.dp),
            border = BorderStroke(2.dp, color = PrimePink)
        ) {
            Text(text = "The New Testament",color = PrimePink)
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun BibleScreenPreview() {
    LeanOnTheme {
        BibleScreen(rememberNavController())
    }
}