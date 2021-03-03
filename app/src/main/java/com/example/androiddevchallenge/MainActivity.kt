package com.example.androiddevchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.composedemo.api.data.CatsModelItem
import com.example.androiddevchallenge.ui.CatsList
import com.example.androiddevchallenge.ui.DetailPage
import com.example.androiddevchallenge.viewmodel.CatsViewModel
import com.mvvmref.utils.Resource

class MainActivity() : AppCompatActivity() {
    val viewModel by viewModels<CatsViewModel>()


    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigation()
        }

    }

    @ExperimentalAnimationApi
    @Composable
    fun ComposeNavigation() {
        val navController = rememberNavController()
        val list: Resource<List<CatsModelItem>> by viewModel.catsData.observeAsState(Resource.loading())

        NavHost(navController = navController, startDestination = "list") {
            composable("list") { CatsList(navController,list = list) }
            composable(
                "detail/{cat}",
                arguments = listOf(
                    navArgument(
                        "cat",
                        builder = {
                            type = NavType.StringType
                        }
                    )
                )
            ) {
                val id = it.arguments?.getString("cat")
                val catModel = viewModel.getCat(id)
                DetailPage(navController, catModel)
            }
        }
        viewModel.getCats()
    }


}