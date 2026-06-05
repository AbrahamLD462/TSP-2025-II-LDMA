package com.example.listacomida_2025_2

import android.graphics.Paint.Style
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.DataSource
import model.Platillo

@Composable
fun MenuCard(platillo: Platillo, modifier: Modifier = Modifier){
    Card(modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clip(RoundedCornerShape(15.dp)),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.LightGray),
            )  {
                Image(
                    painter = painterResource(id = platillo.drawableResId),
                    contentDescription = stringResource(id = platillo.stringResourceId),
                    modifier = Modifier
                        .size(160.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = LocalContext.current.getString(platillo.stringResourceId),
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = LocalContext.current.getString(platillo.stringResourcePrecio),
                        modifier = Modifier.padding(4.dp),
                        fontSize = 20.sp,
                    )
                    Text(
                        text = LocalContext.current.getString(platillo.stringResourceOferta),
                        modifier = Modifier.padding(4.dp),
                        fontSize = 20.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

        }
    }
}

@Composable
fun MenuCardList(platillos: List<Platillo>, modifier: Modifier = Modifier){
    LazyColumn(modifier = Modifier) {
        items(platillos){
            platillo -> MenuCard(
            platillo = platillo,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MenuCard_Preview(){
    val dataSource = DataSource()
    val platillos = dataSource.LoadPlatillos()

    MenuCard(platillo = platillos[0])
}
