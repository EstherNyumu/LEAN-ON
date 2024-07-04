package com.example.leanon.ui.theme.pages.home

import android.content.Intent
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import coil.compose.rememberAsyncImagePainter
import com.example.leanon.data.AuthRepository
import com.example.leanon.data.PostsRepository
import com.example.leanon.models.PostsWithImage
import com.example.leanon.models.UserDets
import com.example.leanon.navigation.ROUTE_ADD_POST
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun HomeScreen(navController:NavHostController) {

    val context = LocalContext.current
    val postsRepository = PostsRepository(navController, context)

    val emptyPostState = remember { mutableStateOf(PostsWithImage("","","","")) }
    val emptyPostsListState = remember { mutableStateListOf<PostsWithImage>() }

    val posts = postsRepository.viewPosts(emptyPostState, emptyPostsListState)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Posts",
            style = TextStyle(Brush.horizontalGradient(listOf(Color(0xFFFF0078), Color(0xFF9C27B0)))),
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn {
            items(posts) {
                PostItem(
                    userName = it.userName,
                    postText = it.postText,
                    imageUrl = it.imageUrl,
                    postId = it.postId,
                    navController = navController,
                    postsRepository = postsRepository
                )
            }
        }
    }
    Column {
        Spacer(modifier = Modifier.weight(1f))
        Row {
            FloatingActionButton(
                onClick = {
                    val authRepository = AuthRepository(navController, context)
                    if (!(authRepository.isLoggedIn())) {
                        navController.navigate(ROUTE_LOGIN)
                    } else {
                        navController.navigate(ROUTE_ADD_POST)
                    }
                },
                containerColor = PrimePink,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    }
}


@Composable
fun PostItem(userName:String,postText:String,imageUrl:String,postId:String,navController:NavHostController,
             postsRepository: PostsRepository
) {

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        var showDialog by remember { mutableStateOf(false) }
        val context = LocalContext.current
        val mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser
        val uid = user?.uid
        var userName1 by remember { mutableStateOf("") }
        LaunchedEffect(uid) {
            if (uid != null) {
                val database = FirebaseDatabase.getInstance()
                val userRef = database.getReference("User Details").child(uid)
                userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val userDets = snapshot.getValue(UserDets::class.java)
                        userName1 = userDets?.username ?: "N/A" // Access username from UserDets object
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Handle potential errors
                        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ){
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "@ $userName",
                modifier = Modifier
                    .padding(10.dp)
                    .align(CenterHorizontally),
                color = PrimePink,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold)
            if (imageUrl == ""){ /*TODO*/} else{
                Image(painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(300.dp)
                        .align(CenterHorizontally))
            }
            Text(text = postText,
                modifier = Modifier
                    .padding(10.dp)
                    .align(CenterHorizontally))
            Row(modifier = Modifier
                .align(CenterHorizontally)){
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_TEXT, postText)
                        context.startActivity(shareIntent)
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = PrimePink
                    )
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share Icon")
                }
                IconButton(
                    onClick = {
                        showDialog = true
                    }, colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = PrimePink
                    ),
                    enabled = userName == userName1
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteForever,
                        contentDescription = "Delete Icon"
                    )
                }
            }

        }
        if (showDialog){
            AlertDialog(
                onDismissRequest = { showDialog = false },
                text = { Text("Are you sure you want to delete?") },
                confirmButton = {
                    Button(onClick = {
                        val authRepository = AuthRepository(navController,context)
                        if(!(authRepository.isLoggedIn())){
                            navController.navigate(ROUTE_LOGIN)
                        }
                        else{
                            postsRepository.deletePost(postId)
                        }
                        showDialog = false
                    },colors = ButtonDefaults.buttonColors(PrimePink)) {
                        Text("Yes")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false },
                        colors = ButtonDefaults.buttonColors(PrimePink)) {
                        Text("No")
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}




@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreview() {
    LeanOnTheme {
        HomeScreen(rememberNavController())
    }
}

