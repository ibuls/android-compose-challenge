package com.example.androiddevchallenge.api.data

import android.content.Context


class CatsRepository(val context: Context) {



   suspend fun getCats() =  CatsService.create().getBreeds()
}