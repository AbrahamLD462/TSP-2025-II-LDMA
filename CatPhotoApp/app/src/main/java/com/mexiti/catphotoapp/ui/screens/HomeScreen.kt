package com.mexiti.catphotoapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mexiti.catphotoapp.Model.CatPhoto
import com.mexiti.catphotoapp.R
import com.mexiti.catphotoapp.viewmodel.CatUIstate

@Composable

fun HomeScreen(
    catUiState:CatUIstate,
    modifier: Modifier= Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
               ){
    when(catUiState){
        is CatUIstate.Loading -> LoadingScreen(modifier.fillMaxSize())
        is CatUIstate.Success -> PhotosGridScreen(catUiState.photos)
        is CatUIstate.Error -> ErrorScreen(Modifier.fillMaxSize())
    }

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier,
        contentAlignment = Alignment.Center){
        Image(painter = painterResource(R.drawable.loader),
            contentDescription = "Loading")
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier,
        contentAlignment = Alignment.Center){
        Image(painter = painterResource(R.drawable.error_load),
            contentDescription = "Error")
    }
}

@Composable
fun CatPhotoCard(photo: CatPhoto,modifier: Modifier){
    Card (
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.url)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.cat_image),
            modifier = modifier.fillMaxWidth(),
            error = painterResource(R.drawable.error_404),
            placeholder = painterResource(R.drawable.carga),
            contentScale = ContentScale.Crop,

            )
    }
}

@Composable
fun PhotosGridScreen(
    photos:List<CatPhoto>,
    modifier: Modifier= Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = contentPadding
    ) {
        items(
            items = photos,
            key = {photo -> photo.id}
        ){
            photoCard -> CatPhotoCard(
                photo = photoCard,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}


@Composable
fun ResultScreen(photos:String, modifier: Modifier = Modifier){
    Box(modifier = modifier,
        contentAlignment = Alignment.Center
        ){
        Text(text = photos )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeScreen(catUiState = CatUIstate.Success(emptyList()))
    }
}
