package com.example.leanon.ui.theme.pages.notepad

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.leanon.data.SermonsRepository
import com.example.leanon.models.Sermons
import com.example.leanon.navigation.ROUTE_ADD_SERMON
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.ui.theme.PrimePink

@Composable
fun SermonNotepadScreen(navController:NavHostController) {
        val context = LocalContext.current
        val sermonsRepository = SermonsRepository(navController, context)


        val emptySermonState = remember { mutableStateOf(Sermons("","","","","","")) }
        val emptySermonsListState = remember { mutableStateListOf<Sermons>() }

        val sermons = sermonsRepository.viewSermon(emptySermonState, emptySermonsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "My Sermons",
                style = TextStyle(Brush.horizontalGradient(listOf(Color(0xFFFF0078), Color(0xFF9C27B0)))),
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn {
                items(sermons) {
                    SermonsItem(
                        sermonDate = it.sermonDate,
                        preacher = it.preacher,
                        sermonScripture = it.sermonScripture,
                        sermonTopic = it.sermonTopic,
                        sermonNotes = it.sermonNotes,
                        sermonId = it.sermonId,
                        sermonsRepository = sermonsRepository
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
                        navController.navigate(ROUTE_ADD_SERMON)
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
fun SermonsItem(
    sermonDate: String,
    preacher: String,
    sermonScripture: String,
    sermonTopic: String,
    sermonNotes: String,
    sermonId: String,
    sermonsRepository: SermonsRepository
) {

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var showDialog by remember { mutableStateOf(false) }
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            ),
//            elevation = CardDefaults.elevatedCardElevation(4.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
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
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {},
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
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.DeleteForever,
                        contentDescription = "Delete Icon"
                    )
                }
            }
            if (showDialog){
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    text = { Text("Are you sure you want to delete?") },
                    confirmButton = {
                        Button(onClick = {
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
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
