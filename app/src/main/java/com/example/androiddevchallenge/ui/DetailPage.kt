package com.example.androiddevchallenge.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.androiddevchallenge.R
import com.example.composedemo.api.data.CatsModelItem
import dev.chrisbanes.accompanist.glide.GlideImage

@ExperimentalAnimationApi
    @Composable
    fun DetailPage(navController: NavController, cat: CatsModelItem?) {

        Column(
            modifier = Modifier
                .background(colorResource(id = R.color.lightCream))
                .fillMaxHeight()
                .fillMaxWidth()
            
        ) {
            TopAppBar(

                title = {
                    Text(text = cat?.name?:"Detail Page", color = MaterialTheme.colors.onPrimary)
                },
                backgroundColor = colorResource(id = R.color.cream),
               navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            tint = colorResource(id = R.color.white),
                            contentDescription = null
                        )
                    }
                }
            )


            cat?.let {
                GlideImage(
                    data = it.image?.url ?: "", contentDescription = null,

                    modifier = Modifier
                        .height(220.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )


                Row(Modifier.padding(horizontal = 10.dp,vertical = 10.dp)) {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),text = "Name: ",fontSize = 18.sp,color = colorResource(id = R.color.black))
                    Text(modifier = Modifier.align(Alignment.CenterVertically),text = it.name,fontSize = 16.sp,color = colorResource(id = R.color.darkBrownColor))
                }
                Text(modifier = Modifier.padding(start = 10.dp, end = 10.dp,top = 10.dp),text = "Description: ",fontSize = 18.sp,color = colorResource(id = R.color.black))
                Text(modifier = Modifier.padding(horizontal = 10.dp),text = it.description,fontSize = 16.sp,color = colorResource(id = R.color.darkBrownColor))
                Text(modifier = Modifier.padding(start = 10.dp, end = 10.dp,top = 10.dp),text = "Temperament: ",fontSize = 18.sp,color = colorResource(id = R.color.black))
                Text(modifier = Modifier.padding(horizontal = 10.dp),text = it.temperament,fontSize = 16.sp,color = colorResource(id = R.color.darkBrownColor))

                Row(Modifier.padding(horizontal = 10.dp,vertical = 10.dp)) {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),text = "Life Span: ",fontSize = 18.sp,color = colorResource(id = R.color.black))
                    Text(modifier = Modifier.align(Alignment.CenterVertically),text = it.life_span,fontSize = 16.sp,color = colorResource(id = R.color.darkBrownColor))
                }
                Row(Modifier.padding(horizontal = 10.dp,vertical = 10.dp)) {
                    Text(modifier = Modifier.align(Alignment.CenterVertically),text = "Alternate Names: ",fontSize = 18.sp,color = colorResource(id = R.color.black))
                    Text(modifier = Modifier.align(Alignment.CenterVertically),text = if (it.alt_names?.trim()?.length?:0>0)it.alt_names!! else "NA",fontSize = 16.sp,color = colorResource(id = R.color.darkBrownColor))
                }
            }
        }
    }

