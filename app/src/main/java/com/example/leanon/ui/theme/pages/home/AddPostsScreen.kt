package com.example.leanon.ui.theme.pages.home

import android.content.res.Configuration
import android.net.Uri
import android.provider.MediaStore
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
import com.example.leanon.ui.theme.LeanOnTheme

@Composable
fun AddPostsScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        var context = LocalContext.current
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

        var anonymousName by remember { mutableStateOf("") }
        var postText by remember { mutableStateOf("") }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(value = anonymousName,
            onValueChange = {anonymousName=it},
            label = { Text(text = "Anonymous Name")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
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
                        postsRepository.savePostWithImage(anonymousName, postText, imageUri!!)
                        navController.navigate(BottomBarScreen.Home.route)
                    }
                    else{
                        postsRepository.savePostWithoutImage(anonymousName,postText)

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