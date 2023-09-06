package com.example.leanon.ui.theme.pages.notepad

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.leanon.ui.theme.PrimePink

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
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "My Prayer",
            fontSize = 30.sp,
            color = PrimePink,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
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
        Button(onClick = {
            var prayersRepository = PrayersRepository(navController,context)
            prayersRepository.savePrayer(prayerDate,prayerText)
            navController.navigate(ROUTE_PRAYER_NOTEPAD)
        },
            colors = ButtonDefaults.buttonColors(PrimePink)) {
            Text(text = "Amen!")
        }
    }
}
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AddPrayerScreenPreview() {
    LeanOnTheme {
        AddPrayerScreen(rememberNavController())
    }
}