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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.data.SermonsRepository
import com.example.leanon.navigation.ROUTE_SERMON_NOTEPAD
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSermonScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var context = LocalContext.current
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Sermon of the Day",
            fontSize = 30.sp,
            color = PrimePink,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Spacer(modifier = Modifier.height(10.dp))
        var sermonDate by remember { mutableStateOf("") }
        var preacher by remember { mutableStateOf("") }
        var sermonScripture by remember { mutableStateOf("") }
        var sermonTopic by remember { mutableStateOf("") }
        var sermonNotes by remember { mutableStateOf("") }
        OutlinedTextField(
            value = sermonDate,
            onValueChange = {sermonDate=it},
            label = { Text(text = "Date...")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = preacher,
            onValueChange = {preacher=it},
            label = { Text(text = "Preacher...")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = sermonScripture,
            onValueChange = {sermonScripture=it},
            label = { Text(text = "Scripture...")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)

        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = sermonTopic,
            onValueChange = {sermonTopic=it},
            label = { Text(text = "Topic...")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = sermonNotes,
            onValueChange = {sermonNotes=it},
            label = { Text(text = "Note to take home...")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.height(300.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
//        Button(onClick = {
//            var sermonsRepository = SermonsRepository(navController, context)
//            sermonsRepository.saveSermon(sermonDate, preacher, sermonScripture, sermonTopic, sermonNotes)
//            navController.navigate(ROUTE_SERMON_NOTEPAD)
//        },
//            colors = ButtonDefaults.buttonColors(PrimePink)) {
//            Text(text = "Done!")
//        }
        val verticalGradient = Brush.verticalGradient(
            colors = listOf(Color(0xFFFF0078), Color(0xFF9C27B0)),
            startY = 0f,
            endY = 100f
        )
        Text(text = "Done",
            modifier = Modifier
//                .padding(10.dp)
                .clickable(onClick = {
                    var sermonsRepository = SermonsRepository(navController, context)
                    sermonsRepository.saveSermon(sermonDate, preacher, sermonScripture, sermonTopic, sermonNotes)
                    navController.navigate(ROUTE_SERMON_NOTEPAD)
                })
                .clip(RoundedCornerShape(10.dp))
                .background(brush = verticalGradient)
                .padding(12.dp),
//                .width(1,
            color = Color.White
        )

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun AddSermonScreenPreview() {
    LeanOnTheme {
        AddSermonScreen(rememberNavController())
    }
}