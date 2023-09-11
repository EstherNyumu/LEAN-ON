package com.example.leanon.ui.theme.pages.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.leanon.data.AuthRepository
import com.example.leanon.models.User
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.ui.theme.PrimePink

//@Composable
//fun ProfileScreen(navController: NavHostController) {
//    Column(modifier = Modifier
//        .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ){
//        var context = LocalContext.current
//        Text(
//            text = "My Profile",
//            fontSize = 30.sp,
//            color = PrimePink,
//            modifier = Modifier.padding(20.dp),
//            fontWeight = FontWeight.Bold,
//            fontFamily = FontFamily.Monospace
//        )
//        Text(text = "Name: ", fontFamily = FontFamily.Monospace)
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Text(text = "Email: ",fontFamily = FontFamily.Monospace)
//        Spacer(modifier = Modifier.height(20.dp))
//
//        ClickableText(
//            text = AnnotatedString("Change Password"),
//            onClick = {/*how to change pass*/},
//            style = TextStyle(
//                fontFamily = FontFamily.Monospace,
//                color = PrimePink
//            ))
//        Spacer(modifier = Modifier.height(20.dp))
//
//        ClickableText(
//            text = AnnotatedString("Need any help?"),
//            onClick = {/*contact customer care*/},
//            style = TextStyle(
//                fontFamily = FontFamily.Monospace,
//                color = PrimePink
//            ))
//        Spacer(modifier = Modifier.height(20.dp))
//
//        ClickableText(
//            text = AnnotatedString("Log out"),
//            onClick = {
//                var authRepository = AuthRepository(navController, context)
//                authRepository.logout()
//                navController.navigate(ROUTE_LOGIN)
//            },
//            style = TextStyle(
//                fontFamily = FontFamily.Monospace,
//                color = PrimePink
//            )
//        )
//
//    }
//}
@Composable
fun ProfileScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var authRepository = AuthRepository(navController, context)


        val emptyAuthState = remember { mutableStateOf(User("","","","")) }
        var emptyAuthsListState = remember { mutableStateListOf<User>() }

        var auths = authRepository.viewAuth(emptyAuthState, emptyAuthsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
            text = "My Profile",
            fontSize = 30.sp,
            color = PrimePink,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(auths){
                  AuthItem(
                      username = it.username,
                      email = it.email,
                      userId = it.userId,
                      navController = navController,
                      authRepository = authRepository
                  )
                }
            }
        }
    }
}


@Composable
fun AuthItem(username:String, email:String,userId:String,
                navController:NavHostController, authRepository:AuthRepository) {

    Column(modifier = Modifier.fillMaxWidth()) {
        var context = LocalContext.current
        Text(text = "Name: ", fontFamily = FontFamily.Monospace)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = username)
        Text(text = "Email: ",fontFamily = FontFamily.Monospace)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = email)
        ClickableText(
            text = AnnotatedString("Change Password"),
            onClick = {/*how to change pass*/},
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                color = PrimePink
            ))
        Spacer(modifier = Modifier.height(20.dp))

        ClickableText(
            text = AnnotatedString("Need any help?"),
            onClick = {/*contact customer care*/},
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                color = PrimePink
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        ClickableText(
            text = AnnotatedString("Log out"),
            onClick = {
                var authRepository = AuthRepository(navController, context)
                authRepository.logout()
                navController.navigate(ROUTE_LOGIN)
            },
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                color = PrimePink
            )
        )

//        Button(onClick = {
//            productRepository.deleteProduct(id)
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
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun ProfileScreenPreview() {
//    LeanOnTheme {
//        ProfileScreen(rememberNavController())
//
//    }
//}