package com.mvvmref.utils

data class Resource<T>(val status: Status,val data:T?,val message:String?) {
    companion object {

        fun <T> success(data:T):Resource<T> = Resource(Status.SUCCESS,data,null)

        fun <T> error(data:T? = null,message: String) = Resource(Status.ERROR,data,message)

        fun <T> loading(data:T? = null) = Resource(Status.LOADING,data,null)
    }
}