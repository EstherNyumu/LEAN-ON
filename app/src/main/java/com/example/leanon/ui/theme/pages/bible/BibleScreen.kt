package com.example.leanon.ui.theme.pages.bible

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.leanon.models.UserApi
import com.example.leanon.ui.theme.LeanOnTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Objects

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibleScreen(navController: NavHostController) {
    var context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
      /*  IconButton(onClick = { *//*TODO*//* },
            colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Transparent,
                contentColor = PrimePink
            )) {
            Icon(
                Icons.Outlined.Book,
                contentDescription ="Book Icon",
                modifier = Modifier
                    .height(500.dp)
                    .width(500.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "I want to read: ",
            color = Color.DarkGray,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                      navController.navigate(ROUTE_OLD_TESTAMENT)
            },
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(20.dp),
//            border = BorderStroke(2.dp, color = PrimePink)
            border = BorderStroke(3.dp, Brush.horizontalGradient(listOf(Color(0xFFFF0078), Color(0xFF9C27B0))))
        ) {
            Text(text = "The Old Testament", color = PrimePurple)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                      navController.navigate(ROUTE_NEW_TESTAMENT)
            },
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            shape = RoundedCornerShape(20.dp),
//            border = BorderStroke(2.dp, color = PrimePink)
            border = BorderStroke(3.dp, Brush.horizontalGradient(listOf(Color(0xFFFF0078), Color(0xFF9C27B0))))
        ) {
            Text(text = "The New Testament",color = PrimePurple)
        }*/

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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val book = remember { mutableStateOf(TextFieldValue()) }
        val chapter = remember { mutableStateOf(TextFieldValue()) }
        val verse = remember { mutableStateOf(TextFieldValue()) }

        val profile = remember {
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
            text="API Sample",
            style= TextStyle(
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            label = { Text(text = "User ID")},
            value = book.value,
            onValueChange = { book.value = it }
        )
        TextField(
            label = { Text(text = "User ID")},
            value = chapter.value,
            onValueChange = {chapter.value = it }
        )

        TextField(
            label = { Text(text = "User ID")},
            value = verse.value,
            onValueChange = { verse.value = it }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    val data = sendRequest(
                        book = book.value.text,
                        chapter = chapter.value.text,
                        verse = verse.value.text,
                        bibleState = profile
                    )

                    Log.d("Main Activity", profile.toString())
                }
            ) {
                Text(text = "Get Data")
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = profile.component1().toString(), fontSize = 40.sp)
    }
}

fun sendRequest(
    book: String,
    chapter: String,
    verse: String,
    bibleState: MutableState<BibleModel>
) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://bible-api.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UserApi::class.java)

    val call: Call<BibleModel?>? = api.getUserById(book, chapter, verse);

    call!!.enqueue(object: Callback<BibleModel?> {
        override fun onResponse(call: Call<BibleModel?>, response: Response<BibleModel?>) {
            if(response.isSuccessful) {
                Log.d("Main", "my_response_from_api!" + response.body().toString())
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
            Log.e("Main", "Failed mate " + t.message.toString())
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