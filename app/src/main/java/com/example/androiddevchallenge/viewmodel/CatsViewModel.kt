/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.api.data.CatsModelItem
import com.example.androiddevchallenge.api.data.CatsRepository
import com.example.androiddevchallenge.api.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatsViewModel(app: Application) : AndroidViewModel(app) {
    private val TAG = "CatsViewModel"

    val catsData: MutableLiveData<Resource<List<CatsModelItem>>> = MutableLiveData()
    val catsRepository = CatsRepository(app)
    fun getCats() = viewModelScope.launch(Dispatchers.IO) {
        catsData.postValue(Resource.loading(data = null))
        Log.d(TAG, "getCats: loading")
        try {
            Log.d(TAG, "getCats: success")
            catsData.postValue(Resource.success(catsRepository.getCats()))
        } catch (exception: Exception) {
            Log.d(TAG, "getCats: exception : ${exception.localizedMessage}")
            exception.printStackTrace()
            catsData.postValue(Resource.error(null, exception.localizedMessage))
        }
    }

    fun getCat(id: String?): CatsModelItem? {
        return catsData.value?.data?.firstOrNull { it.id == id }
    }
}
