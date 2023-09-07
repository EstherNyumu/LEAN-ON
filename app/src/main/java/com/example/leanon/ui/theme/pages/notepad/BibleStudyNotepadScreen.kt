package com.example.leanon.ui.theme.pages.notepad

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.data.BibleStudyRepository
import com.example.leanon.models.BibleStudy
import com.example.leanon.ui.theme.LeanOnTheme

//@Composable
//fun BibleStudyNotepadScreen(navController: NavHostController) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .verticalScroll(rememberScrollState()),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//            Text(text = "Bible Study Sessions")
//            Spacer(modifier = Modifier.weight(1f))
//            ExtendedFloatingActionButton(
//                onClick = {
//                    navController.navigate(ROUTE_ADD_BIBLE_STUDY)
//                },
//                containerColor = PrimePink,
//                contentColor = Color.White,
//                icon = { Icon(Icons.Filled.Edit, "Extended floating action button.") },
//                text = { Text(text = "Add Bible Study") }
//            )
//    }
//}
@Composable
fun BibleStudyNotepadScreen(navController:NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

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
            Text(text = "Bible Study Sessions",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(studies){
                  StudyItem(
                      studyDate = it.studyDate,
                      studyScripture = it.studyScripture,
                      observation = it.observation,
                      application = it.application,
                      studyPrayer =it.studyPrayer,
                      studyId = it.studyId ,
                      navController = navController,
                      bibleStudyRepository = bibleStudyRepository
                  )
                }
            }
        }
    }
}


@Composable
fun StudyItem(studyDate:String, studyScripture:String, observation:String, application:String,studyPrayer:String,
              studyId:String,navController:NavHostController,bibleStudyRepository:BibleStudyRepository) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = studyDate)
        Text(text = studyScripture)
        Text(text = observation)
        Text(text = application)
        Text(text = studyPrayer)
        Button(onClick = {
            bibleStudyRepository.deleteStudy(studyId)
        }) {
            Text(text = "Delete")
        }
//        Button(onClick = {
//            navController.navigate(ROUTE_UPDATE_PRODUCTS+"/$id")
//        }) {
//            Text(text = "Update")
//        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun BibleStudyNotepadScreenPreview() {
    LeanOnTheme {
        BibleStudyNotepadScreen(rememberNavController())
    }
}