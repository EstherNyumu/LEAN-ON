package com.example.leanon.ui.theme.pages.notepad

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.navigation.ROUTE_BIBLE_STUDY_NOTEPAD
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBibleStudyScreen(navController: NavHostController) {
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
        Text(
            text = "Bible Study",
            fontSize = 30.sp,
            color = PrimePink,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(10.dp))
        var studyDate by remember { mutableStateOf("") }
        var studyScripture by remember { mutableStateOf("") }
        var observation by remember { mutableStateOf("") }
        var application by remember { mutableStateOf("") }
        var studyPrayer by remember { mutableStateOf("") }
        OutlinedTextField(
            value = studyDate,
            onValueChange = {studyDate=it},
            label = { Text(text = "Date...")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = studyScripture,
            onValueChange = {studyScripture=it},
            label = { Text(text = "Scripture...")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = observation,
            onValueChange = {observation=it},
            label = { Text(text = "Observation...")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.height(150.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = application,
            onValueChange = {application=it},
            label = { Text(text = "Application...")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.height(150.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = studyPrayer,
            onValueChange = {studyPrayer=it},
            label = { Text(text = "Prayer...")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.height(150.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            navController.navigate(ROUTE_BIBLE_STUDY_NOTEPAD)
        },
            colors = ButtonDefaults.buttonColors(PrimePink)) {
            Text(text = "Amen!")
        }

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AddBibleStudyScreenPreview() {
    LeanOnTheme {
        AddBibleStudyScreen(rememberNavController())
    }
}