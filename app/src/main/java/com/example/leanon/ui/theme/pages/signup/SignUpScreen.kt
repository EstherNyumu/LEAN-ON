package com.example.leanon.ui.theme.pages.signup

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.data.AuthRepository
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink

@OptIn(ExperimentalMaterial3Api::class)
private class PasswordVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            AnnotatedString("*".repeat(text.text.length)),

            /**
             * [OffsetMapping.Identity] is a predefined [OffsetMapping] that can be used for the
             * transformation that does not change the character count.
             */
            OffsetMapping.Identity
        )
    }
}
@Composable
fun SignUpScreen(navController: NavHostController) {
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        var context  = LocalContext.current
        Text(text = "Sign up Here",
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            style = TextStyle(Brush.horizontalGradient(listOf(Color(0xFFFF0078), Color(0xFF9C27B0)))),
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
        )

        var username by remember { mutableStateOf(TextFieldValue("")) }
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var password by remember { mutableStateOf(TextFieldValue("")) }
        var showPassword by remember { mutableStateOf(false) }

        OutlinedTextField(value = username,
            onValueChange = {username = it},
            label = { Text(text = "UserName..") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier =  Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = email,
            onValueChange = {email = it},
            label = { Text(text = "Email..") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier =  Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value = password,
            onValueChange = {password = it},
            label = { Text(text = "Password..") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if(showPassword){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            },
            trailingIcon = {
                if (showPassword){
                    IconButton(onClick = { showPassword = false}) {
                        Icon(
                            imageVector = Icons.Filled.Visibility ,
                            contentDescription = "hide_password")
                    }
                } else{
                    IconButton(onClick = { showPassword = true }) {
                        Icon(imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "show_password" )
                    }
                }
            },
            modifier =  Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            var authRepository = AuthRepository(navController, context)
            authRepository.signup(username.text.trim(),email.text.trim(),password.text.trim())
            navController.navigate(ROUTE_LOGIN)
        },colors = ButtonDefaults.buttonColors(PrimePink)) {
            Text(text = "Sign Up")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            navController.navigate(ROUTE_LOGIN)
        },colors = ButtonDefaults.buttonColors(PrimePink)) {
            Text(text = "Have account? Login")
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SignUpScreenPreview() {
    LeanOnTheme {
        SignUpScreen(rememberNavController())
    }
}