package com.example.leanon.ui.theme.pages.church

import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        var context = LocalContext.current
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {
                Log.e("tag","URL IS "+ "https://www.google.com/maps/search/redeemed+gospel+church/@-1.2570238,36.8362672,13z/data=!3m1!4b1?entry=ttu")
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/redeemed+gospel+church/@-1.2570238,36.8362672,13z/data=!3m1!4b1?entry=ttu")
                )
                context.startActivity(urlIntent)
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
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
                    modifier = Modifier.padding(10.dp),
                    color = Color.DarkGray
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {
                Log.e("tag","URL IS "+ "https://www.google.com/maps/search/anglican+church+of+Kenya/@-1.2315549,36.750807,12z/data=!3m1!4b1?entry=ttu")
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/anglican+church+of+Kenya/@-1.2315549,36.750807,12z/data=!3m1!4b1?entry=ttu")
                )
                context.startActivity(urlIntent)
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
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
                    modifier = Modifier.padding(10.dp),
                    color = Color.DarkGray
                )
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {
                Log.e("tag","URL IS "+ "https://www.google.com/maps/search/african+inland+church/@-1.246946,36.7468251,11z/data=!3m1!4b1?entry=ttu")
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/african+inland+church/@-1.246946,36.7468251,11z/data=!3m1!4b1?entry=ttu")
                )
                context.startActivity(urlIntent)
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
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
                    modifier = Modifier.padding(10.dp),
                    color = Color.DarkGray
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {
                Log.e("tag","URL IS "+ "https://www.google.com/maps/search/citam/@-1.2462491,36.7468241,11z/data=!3m1!4b1?entry=ttu")
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/citam/@-1.2462491,36.7468241,11z/data=!3m1!4b1?entry=ttu")
                )
                context.startActivity(urlIntent)
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
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
                    modifier = Modifier.padding(10.dp),
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {
                Log.e("tag","URL IS "+ "https://www.google.com/maps/search/deliverance+Church/@-1.2455522,36.7468231,11z/data=!3m1!4b1?entry=ttu")
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/deliverance+Church/@-1.2455522,36.7468231,11z/data=!3m1!4b1?entry=ttu")
                )
                context.startActivity(urlIntent)
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
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
                    modifier = Modifier.padding(10.dp),
                    color = Color.DarkGray
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {
                Log.e("tag","URL IS "+ "https://www.google.com/maps/search/jubilee+Christian+Church/@-1.2448554,36.7468221,11z/data=!3m1!4b1?entry=ttu")
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/jubilee+Christian+Church/@-1.2448554,36.7468221,11z/data=!3m1!4b1?entry=ttu")
                )
                context.startActivity(urlIntent) }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    painter = painterResource(id = R.drawable.jubilee),
                    contentDescription = "jubilee"
                )
            }
            Row( modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Jubilee Christian Church",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp),
                    color = Color.DarkGray
                )
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {
                Log.e("tag","URL IS "+ "https://www.google.com/maps/search/pcea/@-1.2441585,36.7468211,11z/data=!3m1!4b1?entry=ttu")
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/pcea/@-1.2441585,36.7468211,11z/data=!3m1!4b1?entry=ttu")
                )
                context.startActivity(urlIntent)
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    painter = painterResource(id = R.drawable.pcea),
                    contentDescription = "pcea"
                )
            }
            Row( modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Presbyterian Church of East Africa(PCEA)",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp),
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            onClick = {
                Log.e("tag","URL IS "+ "https://www.google.com/maps/search/winner's+chapel/@-1.2434617,36.7468201,11z/data=!3m1!4b1?entry=ttu")
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.google.com/maps/search/winner's+chapel/@-1.2434617,36.7468201,11z/data=!3m1!4b1?entry=ttu")
                )
                context.startActivity(urlIntent)
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    painter = painterResource(id = R.drawable.winnerschapel),
                    contentDescription = "winners"
                )
            }
            Row( modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Winners Chapel Kenya",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp),
                    color = Color.DarkGray
                )
            }

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