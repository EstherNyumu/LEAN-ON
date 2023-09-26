package com.example.leanon.ui.theme.pages.notepad

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.data.PrayersRepository
import com.example.leanon.navigation.ROUTE_PRAYER_NOTEPAD
import com.example.leanon.ui.theme.LeanOnTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPrayerScreen(navController: NavHostController) {
    var context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "My Prayer",
            style = TextStyle(Brush.horizontalGradient(listOf(Color(0xFFFF0078), Color(0xFF9C27B0)))),
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))
        var prayerDate by remember { mutableStateOf("") }
        var prayerText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = prayerDate,
            onValueChange = {prayerDate=it},
            label = { Text(text = "Date...")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = prayerText,
            onValueChange = {prayerText=it},
            label = { Text(text = "Dear God...")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text) ,
            modifier = Modifier.height(300.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        val verticalGradient = Brush.verticalGradient(
            colors = listOf(Color(0xFFFF0078), Color(0xFF9C27B0)),
            startY = 0f,
            endY = 100f
        )
        Text(text = "Amen!",
            modifier = Modifier
                .clickable(onClick = {
                    var prayersRepository = PrayersRepository(navController,context)
                    prayersRepository.savePrayer(prayerDate,prayerText)
                    navController.navigate(ROUTE_PRAYER_NOTEPAD)
                })
                .clip(RoundedCornerShape(10.dp))
                .background(brush = verticalGradient)
                .padding(12.dp),
            color = Color.White
        )
    }
}
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AddPrayerScreenPreview() {
    LeanOnTheme {
        AddPrayerScreen(rememberNavController())
    }
}