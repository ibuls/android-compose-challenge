package com.example.androiddevchallenge.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.R
import com.example.composedemo.api.data.CatsModelItem
import com.mvvmref.utils.Resource
import com.mvvmref.utils.Status
import dev.chrisbanes.accompanist.glide.GlideImage


@ExperimentalAnimationApi
@Composable
fun CatsList(navController: NavHostController,list:Resource<List<CatsModelItem>>) {
    Column(  modifier = Modifier.background(colorResource(id = R.color.lightCream))

    ) {
        TopAppBar(
            title = {
                Text(text = "Kitty Cat", color = MaterialTheme.colors.onPrimary)
            },
            backgroundColor = colorResource(id = R.color.cream)
            )
        setupListData(navController,list)
    }
}

@ExperimentalAnimationApi
@Composable
fun setupListData(navController: NavHostController,list:Resource<List<CatsModelItem>>) {
    catsList(resp = list){
        navController.navigate("detail/${it.id}")

    }

}


@ExperimentalAnimationApi
@Composable
fun catsList(resp: Resource<List<CatsModelItem>>, catListener: (CatsModelItem) -> Unit) {
    when (resp.status) {
        Status.SUCCESS -> {
            resp.data?.let {
                LazyColumn(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    items(items = it, itemContent = { cat -> CatItem(cat = cat, catListener) })
                }
            }

        }
        Status.ERROR -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {  Text(text = "No Data Found!!",modifier = Modifier.align(alignment = Alignment.Center))}
        }
        Status.LOADING -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center),color = colorResource(
                    id = R.color.cream
                ))

            }
        }
    }

}


@ExperimentalAnimationApi
@Composable
fun CatItem(cat: CatsModelItem, catListener: (CatsModelItem) -> Unit) {

    val isVisible = remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp)) {
        GlideImage(
            data = cat.image?.url ?: "", contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    isVisible.value = !isVisible.value
                },
            contentScale = ContentScale.Crop,
           loading = {
                Box(Modifier.matchParentSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center),color = colorResource(
                        id = R.color.cream
                    ))
                }
            }
        )
        AnimatedVisibility(visible = isVisible.value) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(start = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .background(colorResource(R.color.cream))
            ) {
                Text(
                    cat.name,
                    modifier = Modifier
                        .padding(10.dp, 2.dp)
                        .align(Alignment.CenterVertically)
                        .weight(1f),
                    color = colorResource(id = R.color.white),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp
                )

                Text(
                    modifier = Modifier
                        .width(110.dp)
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .align(Alignment.CenterVertically)
                        .clickable {
                            catListener(cat)
                        }
                        .background(
                            color = colorResource(id = R.color.purple_500)
                        )
                        .padding(vertical = 5.dp),
                    text = stringResource(R.string.view_details),
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),

                    )
            }

        }
        Spacer(modifier = Modifier.height(20.dp))

    }
}