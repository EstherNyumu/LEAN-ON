package com.example.leanon.ui.theme.pages.church

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChurchScreen() {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false)}
    var items = remember{ mutableStateListOf(
        "Redeemed Gospel",
        "Deliverance Church") }
    Scaffold {paddingValues ->
        var modifier = Modifier.padding(paddingValues)
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = text ,
            onQueryChange = {
                text = it
            },
            onSearch ={
                items.add(text)
                active = false
            },
            active = active ,
            onActiveChange = {
                active = it
            },
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (active){
                    Icon(
                        modifier = Modifier.clickable {
                            if (text.isNotEmpty()){
                                text = ""
                            }
                            else{
                                active=false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon")
                }
            }) {
            items.forEach{
                Row (modifier = Modifier.padding(all = 14.dp)){
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = Icons.Default.History,
                        contentDescription ="History Icon" )
                    Text(text = it)
                }
            }
        }
    }
}

//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun ChurchScreenPreview() {
//    LeanOnTheme {
//        ChurchScreen(rememberNavController())
//    }
//}