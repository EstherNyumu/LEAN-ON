package com.example.leanon.ui.theme.pages.home

import android.content.Intent
import android.content.res.Configuration
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.leanon.navigation.ROUTE_ADD_POST
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink

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
                    anonymousName = it.anonymousName,
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
//            Spacer(modifier = Modifier.weight(1f))
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
fun PostItem(anonymousName:String,postText:String,imageUrl:String,postId:String,navController:NavHostController,
             postsRepository: PostsRepository
) {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = CenterHorizontally
    ) {
        val context = LocalContext.current
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
//            elevation = CardDefaults.elevatedCardElevation(7.dp),
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ){
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "@ $anonymousName",
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
                Column {
//                    IconButton(
//                        onClick = {
//                            count++
//                        },
//                        colors = IconButtonDefaults.iconButtonColors(
//                            containerColor = Color.Transparent,
//                            contentColor = PrimePink
//                        )
//                    ) {
//                        Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = "ThumbUp Icon")
//                    }
//                    Text(text = "$count likes",color = Color.DarkGray)
                }

//                IconButton(
//                    onClick = {
//                        if (count!=0) {
//                            count--
//                        }
//                    },
//                    colors = IconButtonDefaults.iconButtonColors(
//                        containerColor = Color.Transparent,
//                        contentColor = PrimePink
//                    )
//                ) {
//                    Icon(
//                        imageVector = Icons.Outlined.ThumbDown,
//                        contentDescription = "ThumbDown Icon"
//                    )
//                }
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
                        val authRepository = AuthRepository(navController,context)
                        if(!(authRepository.isLoggedIn())){
                            navController.navigate(ROUTE_LOGIN)
                        }
                        else{
                            postsRepository.deletePost(postId)
                        }

                    }, colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = PrimePink
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteForever,
                        contentDescription = "Delete Icon"
                    )
                }
            }

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

