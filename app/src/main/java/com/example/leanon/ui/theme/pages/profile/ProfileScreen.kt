package com.example.leanon.ui.theme.pages.profile

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.data.AuthRepository
import com.example.leanon.models.UserDets
import com.example.leanon.ui.theme.LeanOnTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun ProfileScreen(mAuth: FirebaseAuth,navController: NavHostController) {
    val user = mAuth.currentUser
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val authRepository = AuthRepository(navController, context)
    val uid = user?.uid

    LaunchedEffect(user) {
        user?.let {
            email= it.email ?: "N/A"
        }
    }
    LaunchedEffect(uid) {
        if (uid != null) {
            val database = FirebaseDatabase.getInstance()
            val userRef = database.getReference("User Details").child(uid)
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userDets = snapshot.getValue(UserDets::class.java)
                    userName = userDets?.username ?: "N/A" // Access username from UserDets object
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle potential errors
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


    Column(modifier = Modifier.fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally) {

       Spacer(modifier = Modifier.height(50.dp))
       Text(
           text = "Profile",
           style = TextStyle(
               Brush.horizontalGradient(
                   listOf(
                       Color(0xFFFF0078),
                       Color(0xFF9C27B0)
                   )
               )
           ),
           fontSize = 30.sp,
           fontFamily = FontFamily.Monospace,
           fontWeight = FontWeight.Bold
       )
       Spacer(modifier = Modifier.height(20.dp))
       Text(text = "Username: $userName")
       Spacer(modifier = Modifier.height(20.dp))
       Text(text = "Email: $email")
       Spacer(modifier = Modifier.height(20.dp))
       Button(onClick = {
           authRepository.logout()
       }) {
           Text(text = "Sign Out")
       }
       Spacer(modifier = Modifier.height(20.dp))
   }
    Column {
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Text(text = "Help?",
                modifier = Modifier.padding(20.dp)
            )
        }
    }

   }


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ProfileScreenPreview() {
    LeanOnTheme {
        val auth = FirebaseAuth.getInstance()
        ProfileScreen(mAuth = auth,rememberNavController())
    }
}