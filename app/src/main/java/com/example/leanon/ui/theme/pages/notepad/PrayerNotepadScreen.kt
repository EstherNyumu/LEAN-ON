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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.leanon.data.AuthRepository
import com.example.leanon.data.PrayersRepository
import com.example.leanon.models.Prayers
import com.example.leanon.navigation.ROUTE_ADD_PRAYER
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.ui.theme.PrimePink

@Composable
fun PrayerNotepadScreen(navController:NavHostController) {

    val context = LocalContext.current
    val prayersRepository = PrayersRepository(navController, context)


    val emptyPrayerState = remember { mutableStateOf(Prayers("","","")) }
    val emptyPrayersListState = remember { mutableStateListOf<Prayers>() }

    val prayers = prayersRepository.viewPrayers(emptyPrayerState, emptyPrayersListState)


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "My Prayers",
            style = TextStyle(Brush.horizontalGradient(listOf(Color(0xFFFF0078), Color(0xFF9C27B0)))),
            fontSize = 30.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(prayers) {
                PrayerItem(
                    prayerDate = it.prayerDate,
                    prayerText = it.prayerText,
                    prayerId = it.prayerId,
                    prayersRepository = prayersRepository
                )
            }
        }
    }
    Column {
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Spacer(modifier = Modifier.weight(1f))
            FloatingActionButton(
                onClick = {
                    val authRepository = AuthRepository(navController, context)
                    if (!(authRepository.isLoggedIn())) {
                        navController.navigate(ROUTE_LOGIN)
                    } else {
                        navController.navigate(ROUTE_ADD_PRAYER)
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
fun PrayerItem(
    prayerDate: String, prayerText: String, prayerId: String,
    prayersRepository: PrayersRepository
) {

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        val context = LocalContext.current
        OutlinedCard (
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
//            elevation = CardDefaults.elevatedCardElevation(4.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
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
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_TEXT, prayerText)
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
                        prayersRepository.deletePrayer(prayerId)
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

