package com.example.leanon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.navigation.NavigationGraph
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.pages.BottomBar

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeanOnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
                val navController: NavHostController = rememberNavController()
                val bottomBarHeight = 56.dp
                val bottomBarOffsetHeightPx = remember{ mutableStateOf(0f) }
                val buttonsVisible = remember{ mutableStateOf(true) }
                Scaffold (
                    bottomBar = {
                        BottomBar(
                            navController = navController,
                            state = buttonsVisible,
                            modifier = Modifier)
                    }
                ){paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)){
                    NavigationGraph(navController = navController)
                }

                }

            }
        }
    }
}
//@Composable
//fun BottomBar(navController: NavHostController) {
//    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
//        BottomAppBar(contentColor = Color.White,
//            containerColor = PrimePink,
//            actions = {
//                IconButton(onClick = { navController.navigate(ROUTE_HOME) }) {
//                    Icon(Icons.Filled.Home, contentDescription = "Home")
//                }
//                Spacer(modifier = Modifier.width(50.dp))
//                IconButton(onClick = { navController.navigate(ROUTE_BIBLE) }) {
//                    Icon(Icons.Filled.Book, contentDescription = "Bible")
//                }
//                Spacer(modifier = Modifier.width(50.dp))
//                IconButton(onClick = { navController.navigate(ROUTE_NOTEPAD)}) {
//                    Icon(Icons.Filled.Padding, contentDescription = "Notepad")
//                }
//                Spacer(modifier = Modifier.width(50.dp))
//                IconButton(onClick = { navController.navigate(ROUTE_CHURCH) }) {
//                    Icon(Icons.Filled.Church, contentDescription = "Church")
//                }
//            })
//
//    }
//
//}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LeanOnTheme {
//        Greeting("Android")
        BottomBar(navController = rememberNavController(), state = remember { mutableStateOf(true) },
            modifier = Modifier)
    }

}