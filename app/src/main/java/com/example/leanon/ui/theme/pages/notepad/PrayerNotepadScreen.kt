package com.example.leanon.ui.theme.pages.notepad

import android.content.Intent
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.leanon.data.PrayersRepository
import com.example.leanon.models.Prayers
import com.example.leanon.navigation.ROUTE_ADD_PRAYER
import com.example.leanon.ui.theme.PrimePink

//@Composable
//fun PrayerNotepadScreen(navController: NavHostController) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//            Text(text = "My pleads to God")
//            Spacer(modifier = Modifier.weight(1f))
//            ExtendedFloatingActionButton(
//                onClick = {
//                    navController.navigate(ROUTE_ADD_PRAYER)
//                },
//                containerColor = PrimePink,
//                contentColor = Color.White,
//                icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
//                text = { Text(text = "Add Prayer")}
//            )
//    }
//}
@Composable
fun PrayerNotepadScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var prayersRepository = PrayersRepository(navController, context)


        val emptyPrayerState = remember { mutableStateOf(Prayers("","","")) }
        var emptyPrayersListState = remember { mutableStateListOf<Prayers>() }

        var prayers = prayersRepository.viewPrayers(emptyPrayerState, emptyPrayersListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "My Prayers",
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                color = PrimePink)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(prayers){
                    PrayerItem(
                        prayerDate = it.prayerDate,
                        prayerText = it.prayerText,
                        prayerId = it.prayerId,
                        navController = navController,
                        prayersRepository = prayersRepository
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            ExtendedFloatingActionButton(
                onClick = {
                    navController.navigate(ROUTE_ADD_PRAYER)
                },
                containerColor = PrimePink,
                contentColor = Color.White,
                icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
                text = { Text(text = "Add Prayer")}
            )
        }
    }
}


@Composable
fun PrayerItem(prayerDate:String,prayerText:String, prayerId:String,
                navController:NavHostController, prayersRepository:PrayersRepository) {

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        ElevatedCard (
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
        ){
            Row {
                Text(text = "Date:", color = PrimePink, modifier = Modifier.padding(5.dp),fontWeight = FontWeight.SemiBold)
                Text(text = prayerDate, modifier = Modifier.padding(5.dp))
            }
            Row {
                Text(text = "Prayer:", color = PrimePink, modifier = Modifier.padding(5.dp),fontWeight = FontWeight.SemiBold)
                Text(text = prayerText, modifier = Modifier.padding(5.dp))
            }

            Row {
                IconButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prayerText)
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
                        prayersRepository.deletePrayer(prayerId)
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
//            prayersRepository.deletePrayer(prayerId)
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

//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun PrayerNotepadScreenPreview() {
//    LeanOnTheme {
//        PrayerNotepadScreen(rememberNavController())
//    }
//}