package com.example.leanon.ui.theme.pages.home

import android.content.res.Configuration
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
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
import com.example.leanon.data.PostsRepository
import com.example.leanon.models.BottomBarScreen
import com.example.leanon.models.UserDets
import com.example.leanon.ui.theme.LeanOnTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun AddPostsScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        var context = LocalContext.current
        var userName by remember { mutableStateOf("") }
        var postText by remember { mutableStateOf("") }
        val mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        val uid = user?.uid
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
        Text(
            text = "New Post",
            fontSize = 30.sp,
            style = TextStyle(Brush.horizontalGradient(listOf(Color(0xFFFF0078), Color(0xFF9C27B0)))),
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        Text(text = "Make sure you add an image when adding your post!!"
        , fontSize = 12.sp, color = Color.Gray)

        Text(
            text = userName,
            fontSize = 15.sp,
            style = TextStyle(Brush.horizontalGradient(listOf(Color(0xFFFF0078), Color(0xFF9C27B0)))),
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )

        Spacer(modifier = Modifier.height(20.dp))
//        OutlinedTextField(value = userName,
//            onValueChange = {userName=it},
//            label = { Text(text = "Anonymous Name")},
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//            modifier = Modifier.fillMaxWidth(0.8f),
//            enabled = false
//        )
        OutlinedTextField(value = postText,
            onValueChange = {postText=it},
            label = { Text(text = "What's on your mind today..")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.height(300.dp)
                .fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        var hasImage by remember { mutableStateOf(false) }
        var imageUri by remember { mutableStateOf<Uri?>(null) }

        val imagePicker = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = { uri ->
                hasImage = uri != null
                imageUri = uri

            }
        )
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }

        IconButton(onClick = {
            imagePicker.launch("image/*")
        }) {
            Icon(imageVector = Icons.Default.AddAPhoto, contentDescription = "Add Photo")
        }
        val verticalGradient = Brush.verticalGradient(
            colors = listOf(Color(0xFFFF0078), Color(0xFF9C27B0)),
            startY = 0f,
            endY = 100f
        )
        Text(text = "Post",
            modifier = Modifier
                .clickable(onClick = {
                    val postsRepository = PostsRepository(navController, context)
                    if(imageUri != null){
                        /*----Saving Data to Database Logic---*/
                        postsRepository.savePostWithImage(userName, postText, imageUri!!)
                        navController.navigate(BottomBarScreen.Home.route)
                    }
                    else{
                        postsRepository.savePostWithoutImage(userName,postText)

                    }

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
fun AddPostsScreenPreview() {
    LeanOnTheme {
        AddPostsScreen(rememberNavController())
    }
}