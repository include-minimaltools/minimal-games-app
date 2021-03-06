package com.minimaltools.minimalgamesapp.view.network

interface Callback<T> {
    fun onSuccess(result: T?)
    fun onFailed(exception: Exception)
}
