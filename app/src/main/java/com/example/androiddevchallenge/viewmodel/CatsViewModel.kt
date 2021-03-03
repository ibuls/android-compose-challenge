package com.example.androiddevchallenge.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.composedemo.api.data.CatsModelItem
import com.example.androiddevchallenge.api.data.CatsRepository
import com.mvvmref.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatsViewModel(app: Application) : AndroidViewModel(app){
    private  val TAG = "CatsViewModel"

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
            catsData.postValue(Resource.error(null,exception.localizedMessage))
        }
    }

    fun getCat(id: String?): CatsModelItem? {
        return catsData.value?.data?.firstOrNull { it.id  == id}
    }
}