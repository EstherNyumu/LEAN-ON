package com.example.leanon.ui.theme.pages.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leanon.ui.theme.PrimePink


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(PrimePink)) {
            Text(
                text = "LEAN ON Post",
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier.padding(20.dp),
                fontWeight = FontWeight.Bold
            )
        }
//        Spacer(modifier = Modifier.height(20.dp))
//        Scaffold (bottomBar = { BottomBar(rememberNavController()) })
//        {
//                innerPadding->
//            Column(modifier = Modifier.padding(innerPadding),
//                verticalArrangement = Arrangement.Center) {
//                Text(
//                    text = "Posts",
//                    fontSize = 20.sp,
//                    color = Color.Gray,
//                    fontFamily = FontFamily.Monospace,
//                    fontWeight = FontWeight.Bold)
//
//            }
//        }
    }
}




//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun HomeScreenPreview() {
//    LeanOnTheme {
//        HomeScreen(rememberNavController())
//    }
//}

