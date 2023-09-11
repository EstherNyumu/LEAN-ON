package com.example.leanon.ui.theme.pages.church

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leanon.R
import com.example.leanon.ui.theme.LeanOnTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChurchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var text by rememberSaveable { mutableStateOf("") }
        var active by rememberSaveable { mutableStateOf(false) }
        var items = remember {
            mutableStateListOf(
                "Redeemed Gospel",
                "Deliverance Church"
            )
        }
//        Scaffold { paddingValues ->
//            var modifier = Modifier.padding(paddingValues)
//            SearchBar(
//                modifier = Modifier.fillMaxWidth(),
//                query = text,
//                onQueryChange = {
//                    text = it
//                },
//                onSearch = {
//                    items.add(text)
//                    active = false
//                },
//                active = active,
//                onActiveChange = {
//                    active = it
//                },
//                placeholder = {
//                    Text(text = "Search")
//                },
//                leadingIcon = {
//                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
//                },
//                trailingIcon = {
//                    if (active) {
//                        Icon(
//                            modifier = Modifier.clickable {
//                                if (text.isNotEmpty()) {
//                                    text = ""
//                                } else {
//                                    active = false
//                                }
//                            },
//                            imageVector = Icons.Default.Close,
//                            contentDescription = "Close Icon"
//                        )
//                    }
//                }) {
//                items.forEach {
//                    Row(modifier = Modifier.padding(all = 14.dp)) {
//                        Icon(
//                            modifier = Modifier.padding(end = 10.dp),
//                            imageVector = Icons.Default.History,
//                            contentDescription = "History Icon"
//                        )
//                        Text(text = it)
//                    }
//                }
//            }
//        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {/*clickable func*/ }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.width(100.dp).height(100.dp),
                    painter = painterResource(id = R.drawable.redeemed),
                    contentDescription = "redeemed_image"
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Redeemed Gospel Church",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {/*clickable func*/ }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.width(100.dp).height(100.dp),
                    painter = painterResource(id = R.drawable.ack),
                    contentDescription = "ack"
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Anglican Church of Kenya(ACK)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {/*clickable func*/ }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.width(100.dp).height(100.dp),
                    painter = painterResource(id = R.drawable.aic),
                    contentDescription = "aic"
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "African Inland Church(AIC)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {/*clickable func*/ }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.width(100.dp).height(100.dp),
                    painter = painterResource(id = R.drawable.citam),
                    contentDescription = "citam"
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Christ is the Answer Ministries(CITAM)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {/*clickable func*/ }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.width(100.dp).height(100.dp),
                    painter = painterResource(id = R.drawable.deliverance),
                    contentDescription = "deliverance"
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Deliverance Church",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {/*clickable func*/ }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.width(100.dp).height(100.dp),
                    painter = painterResource(id = R.drawable.jubilee),
                    contentDescription = "jubilee"
                )
            }

            Text(
                text = "Jubilee Christian Center",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {/*clickable func*/ }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.width(100.dp).height(100.dp),
                    painter = painterResource(id = R.drawable.pcea),
                    contentDescription = "pcea"
                )
            }

            Text(
                text = "Presbyterian Church of East Africa",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {/*clickable func*/ }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.width(100.dp).height(100.dp),
                    painter = painterResource(id = R.drawable.winnerschapel),
                    contentDescription = "winners"
                )
            }

            Text(
                text = "Winners Chapel Kenya",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Preview()
@Composable
fun ChurchScreenPreview() {
    LeanOnTheme {
        ChurchScreen()
    }
}