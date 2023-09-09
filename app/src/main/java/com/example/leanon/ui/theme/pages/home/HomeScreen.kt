package com.example.leanon.ui.theme.pages.home

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.data.AuthRepository
import com.example.leanon.data.PostsRepository
import com.example.leanon.models.Posts
import com.example.leanon.models.User
import com.example.leanon.navigation.ROUTE_ADD_POST
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink


//@Composable
//fun HomeScreen(navController: NavHostController) {
//    Column(modifier = Modifier
//        .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally) {
//        var context = LocalContext.current
//        Spacer(modifier = Modifier.weight(1f))
//        FloatingActionButton(onClick = {
//            var authRepository = AuthRepository(navController,context)
//            if(!(authRepository.isLoggedIn())){
//                navController.navigate(ROUTE_LOGIN)
//            }
//            else{
//                navController.navigate(ROUTE_ADD_POST)
//            }
//        },
//            containerColor = PrimePink,
//            contentColor = Color.White,
//            shape = CircleShape
//        ){
//            Icon(Icons.Filled.Add, contentDescription ="Add")
//        }
//    }
//}
@Composable
fun HomeScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var postsRepository = PostsRepository(navController, context)
        var authRepository = AuthRepository(navController, context)

        val emptyPostState = remember { mutableStateOf(Posts("","")) }
        var emptyPostsListState = remember { mutableStateListOf<Posts>() }
        val emptyAuthState = remember { mutableStateOf(User("","","","")) }
        var emptyAuthsListState = remember { mutableStateListOf<User>() }

        var posts = postsRepository.viewPosts(emptyPostState, emptyPostsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "My Posts",
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                color = PrimePink)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn{
                items(posts){
                    PostItem(
                        postText = it.postText,
                        postId = it.postId,
                        navController =navController,
                        postsRepository = postsRepository
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            FloatingActionButton(onClick = {
                var authRepository = AuthRepository(navController,context)
                if(!(authRepository.isLoggedIn())){
                    navController.navigate(ROUTE_LOGIN)
                }
                else{
                    navController.navigate(ROUTE_ADD_POST)
                }
            },
                containerColor = PrimePink,
                contentColor = Color.White,
                shape = CircleShape
            ){
                Icon(Icons.Filled.Add, contentDescription ="Add")
            }
        }
    }
}


@Composable
fun PostItem(postText:String,postId:String,navController:NavHostController,
             postsRepository: PostsRepository
) {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var  context = LocalContext.current
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
        ){
            Text(text = postText,
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center)
            Row{
                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White,
                        contentColor = PrimePink
                    )
                ) {
                    Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = "ThumbUp Icon")
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White,
                        contentColor = PrimePink
                    )
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ThumbDown,
                        contentDescription = "ThumbDown Icon"
                    )
                }
                IconButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_TEXT, postText)
                        context.startActivity(shareIntent)
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White,
                        contentColor = PrimePink
                    )
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share Icon")
                }
                IconButton(
                    onClick = {
                        var authRepository = AuthRepository(navController,context)
                        if(!(authRepository.isLoggedIn())){
                            navController.navigate(ROUTE_LOGIN)
                        }
                        else{
                            postsRepository.deletePost(postId)
                        }

                    }, colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White,
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

//        Button(onClick = {
//            postsRepository.deletePost(postId)
//        }) {
//            Text(text = "Delete")
//        }
//        Button(onClick = {
//            navController.navigate(ROUTE_UPDATE_PRODUCTS+"/$id")
//        }) {
//            Text(text = "Update")
//        }
    }
}




@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreview() {
    LeanOnTheme {
        HomeScreen(rememberNavController())
    }
}

