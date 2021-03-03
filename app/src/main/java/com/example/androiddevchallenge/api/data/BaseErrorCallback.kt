package com.example.composedemo.api.data

interface BaseErrorCallback {
    fun onError(error: String, code: Int)
}