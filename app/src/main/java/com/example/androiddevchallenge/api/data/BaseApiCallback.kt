package com.empida.view.repository

import com.example.composedemo.api.data.BaseErrorCallback

interface BaseApiCallback<T> : BaseErrorCallback {
    fun onResponse(response: T)
    fun onResponse(response: T,message:String){}
    fun onShowErrorScreen(){

    }
}