package com.example.leanon.ui.theme.pages.notepad

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
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
import com.example.leanon.data.BibleStudyRepository
import com.example.leanon.models.BibleStudy
import com.example.leanon.navigation.ROUTE_ADD_BIBLE_STUDY
import com.example.leanon.navigation.ROUTE_LOGIN
import com.example.leanon.ui.theme.PrimePink

@Composable
fun BibleStudyNotepadScreen(navController:NavHostController) {
        var context = LocalContext.current
        var bibleStudyRepository = BibleStudyRepository(navController, context)

        val emptyStudyState = remember { mutableStateOf(BibleStudy("","","","","","")) }
        var emptyStudiesListState = remember { mutableStateListOf<BibleStudy>() }

        var studies = bibleStudyRepository.viewStudy(emptyStudyState, emptyStudiesListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Bible Study Sessions",
                style = TextStyle(Brush.horizontalGradient(listOf(Color(0xFFFF0078), Color(0xFF9C27B0)))),
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn() {
                items(studies) {
                    StudyItem(
                        studyDate = it.studyDate,
                        studyScripture = it.studyScripture,
                        observation = it.observation,
                        application = it.application,
                        studyPrayer = it.studyPrayer,
                        studyId = it.studyId,
                        navController = navController,
                        bibleStudyRepository = bibleStudyRepository
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
                        var authRepository = AuthRepository(navController, context)
                        if (!(authRepository.isLoggedIn())) {
                            navController.navigate(ROUTE_LOGIN)
                        } else {
                            navController.navigate(ROUTE_ADD_BIBLE_STUDY)
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
fun StudyItem(studyDate:String, studyScripture:String, observation:String, application:String,studyPrayer:String,
              studyId:String,navController:NavHostController,bibleStudyRepository:BibleStudyRepository) {

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        ElevatedCard (
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            modifier = Modifier.width(300.dp)
        ){
            Row {
                Text(text = "Date:", color = PrimePink,modifier = Modifier.padding(5.dp),fontWeight = FontWeight.SemiBold)
                Text(text = studyDate, modifier = Modifier.padding(5.dp), color = Color.DarkGray)
            }
            Row {
                Text(text = "Scripture:",color = PrimePink,modifier = Modifier.padding(5.dp),fontWeight = FontWeight.SemiBold)
                Text(text = studyScripture, modifier = Modifier.padding(5.dp),color = Color.DarkGray)
            }
            Row {
                Text(text = "Observation:",color = PrimePink,modifier = Modifier.padding(5.dp),fontWeight = FontWeight.SemiBold)
                Text(text = observation, modifier = Modifier.padding(5.dp),color = Color.DarkGray)
            }
            Row {
                Text(text = "Application:",color = PrimePink,modifier = Modifier.padding(5.dp),fontWeight = FontWeight.SemiBold)
                Text(text = application, modifier = Modifier.padding(5.dp),color = Color.DarkGray)
            }
            Row {
                Text(text = "Prayer:",color = PrimePink,modifier = Modifier.padding(5.dp),fontWeight = FontWeight.SemiBold)
                Text(text = studyPrayer, modifier = Modifier.padding(5.dp),color = Color.DarkGray)
            }
            Row {
                IconButton(
                    onClick = {
                        bibleStudyRepository.deleteStudy(studyId)
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
    }
}

