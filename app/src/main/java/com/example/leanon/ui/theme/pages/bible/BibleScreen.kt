package com.example.leanon.ui.theme.pages.bible

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.models.UserApi
import com.example.leanon.ui.theme.LeanOnTheme
import com.example.leanon.ui.theme.PrimePurple
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Objects

@Composable
fun BibleScreen(navController: NavHostController) {
    var context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally){
        BibleCode()

    }
}

data class BibleModel(
    var reference: String,
    var verses: Array<Objects>,
    var text: String,
    var translation_id: String,
    var translation_name: String,
    var translation_note: String,
)



@Composable
fun BibleCode() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val book = remember { mutableStateOf(TextFieldValue()) }
        val chapter = remember { mutableStateOf(TextFieldValue()) }
        val verse = remember { mutableStateOf(TextFieldValue()) }

        val theVerses = remember {
            mutableStateOf(BibleModel(
                reference = "",
                verses = arrayOf(),
                text = "",
                translation_id="",
                translation_name= "",
                translation_note=""
            ))
        }

        Text(
            text="Bible Verses",
            fontSize = 30.sp,
            style = TextStyle(Brush.horizontalGradient(listOf(Color(0xFFFF0078), Color(0xFF9C27B0)))),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            label = { Text(text = "Book")},
            value = book.value,
            onValueChange = { book.value = it }
        )
//        Spacer(modifier = Modifier.height(20.dp))
//        OutlinedTextField(
//            label = { Text(text = "Chapter")},
//            value = chapter.value,
//            onValueChange = {chapter.value = it }
//        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            label = { Text(text = "Chapter")},
            value = verse.value,
            onValueChange = { verse.value = it }
        )

        Spacer(modifier = Modifier.height(15.dp))
        val verticalGradient = Brush.verticalGradient(
            colors = listOf(Color(0xFFFF0078), Color(0xFF9C27B0)),
            startY = 0f,
            endY = 100f
        )
        Text(text = "Get Verse",
            modifier = Modifier
                .clickable(onClick = {
                    val data = sendRequest(
                        book = book.value.text,
                        chapter = "0",
                        verse = verse.value.text,
                        bibleState = theVerses
                    )
                    Log.d("this_pro_data", data.toString())
                })
                .clip(RoundedCornerShape(10.dp))
                .background(brush = verticalGradient)
                .padding(12.dp),
            color = Color.White
        )
        Text(
            modifier = Modifier.padding(20.dp)
                .align(CenterHorizontally),
            text = book.value.text+ " "+verse.value.text,
            fontSize = 30.sp,
            style = TextStyle(
                Brush.horizontalGradient(
                    listOf(
                        Color(0xFFFF0078),
                        Color(0xFF9C27B0)
                    )
                )
            ),
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Monospace
        )
        Text(text = theVerses.value.text,
            fontSize = 20.sp,
            modifier = Modifier.padding(20.dp)
                .align(CenterHorizontally),
            color = PrimePurple)
    }
}


fun sendRequest(
    book: String,
    chapter: String,
    verse: String,
    bibleState: MutableState<BibleModel>
) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://bible-api.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UserApi::class.java)

    val call: Call<BibleModel?>? = api.getUserById(book, chapter, verse);

    call!!.enqueue(object: Callback<BibleModel?> {
        override fun onResponse(call: Call<BibleModel?>, response: Response<BibleModel?>) {
            if(response.isSuccessful) {
                Log.d("my_response_from_api", "my_response_from_api!" + response.body().toString())
                bibleState.value = BibleModel(
                    response.body()!!.reference,
                    response.body()!!.verses,
                    response.body()!!.text,
                    response.body()!!.translation_id,
                    response.body()!!.translation_name,
                    response.body()!!.translation_note
                )
            }
        }

        override fun onFailure(call: Call<BibleModel?>, t: Throwable) {
            Log.d("error_my_response", "Failed mate " + t.message.toString())
        }
    })
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun BibleScreenPreview() {
    LeanOnTheme {
        BibleScreen(rememberNavController())
    }
}