package com.example.leanon.ui.theme.pages.notepad

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.data.SermonsRepository
import com.example.leanon.models.Sermons
import com.example.leanon.navigation.ROUTE_ADD_SERMON
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePink

//@Composable
//fun SermonNotepadScreen(navController: NavHostController) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//           Text(text = "My Sermons")
//           Spacer(modifier = Modifier.weight(1f))
//           ExtendedFloatingActionButton(
//               onClick = {
//                   navController.navigate(ROUTE_ADD_SERMON)
//               },
//               containerColor = PrimePink,
//               contentColor = Color.White,
//               icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
//               text = { Text(text = "Add Sermon")}
//           )
//
//    }
//}
@Composable
fun SermonNotepadScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var sermonsRepository = SermonsRepository(navController, context)


        val emptySermonState = remember { mutableStateOf(Sermons("","","","","","")) }
        var emptySermonsListState = remember { mutableStateListOf<Sermons>() }

        var sermons = sermonsRepository.viewSermon(emptySermonState, emptySermonsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "My Sermons",
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                color = PrimePink)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(sermons){
                    SermonsItem(
                        sermonDate = it.sermonDate,
                        preacher = it.preacher,
                        sermonScripture =it.sermonScripture ,
                        sermonTopic = it.sermonTopic,
                        sermonNotes = it.sermonNotes,
                        sermonId = it.sermonId,
                        navController = navController ,
                        sermonsRepository = sermonsRepository
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            ExtendedFloatingActionButton(
                onClick = {
                    navController.navigate(ROUTE_ADD_SERMON)
                },
                containerColor = PrimePink,
                contentColor = Color.White,
                icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
                text = { Text(text = "Add Sermon")}
            )
        }
    }
}


@Composable
fun SermonsItem(sermonDate:String,preacher:String, sermonScripture:String,sermonTopic:String,sermonNotes:String,
                sermonId:String,navController:NavHostController, sermonsRepository: SermonsRepository
) {

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
        ){
            Row {
                Text(text = "Date:",modifier = Modifier.padding(5.dp), color = PrimePink, fontWeight = FontWeight.SemiBold)
                Text(text = sermonDate, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(text = "Preacher:",modifier = Modifier.padding(5.dp),color = PrimePink,fontWeight = FontWeight.SemiBold)
                Text(text = preacher, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(text = "Scripture:",modifier = Modifier.padding(5.dp),color = PrimePink,fontWeight = FontWeight.SemiBold)
                Text(text = sermonScripture, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(text = "Topic:",modifier = Modifier.padding(5.dp),color = PrimePink,fontWeight = FontWeight.SemiBold)
                Text(text = sermonTopic, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(text = "Notes:",modifier = Modifier.padding(5.dp),color = PrimePink,fontWeight = FontWeight.SemiBold)
                Text(text = sermonNotes, modifier = Modifier.padding(5.dp))
            }
            Row {
                IconButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey download this app from...")
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
                        sermonsRepository.deleteSermon(sermonId)
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
//        Button(onClick = {
//            sermonsRepository.deleteSermon(sermonId)
//        }) {
//            Text(text = "Delete")
//        }
//        Button(onClick = {
//            navController.navigate(ROUTE_UPDATE_PRODUCTS+"/$id")
//        }) {
//            Text(text = "Update")
//        }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SermonNotepadScreenPreview() {
    LeanOnTheme {
        SermonNotepadScreen(rememberNavController())
    }
}