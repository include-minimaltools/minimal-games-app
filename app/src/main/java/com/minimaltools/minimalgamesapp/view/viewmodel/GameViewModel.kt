package com.minimaltools.minimalgamesapp.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.minimaltools.minimalgamesapp.view.model.Game
import com.minimaltools.minimalgamesapp.view.network.Callback
import com.minimaltools.minimalgamesapp.view.network.FirestoreService

class GameViewModel : ViewModel() {
    private val service = FirestoreService()
    var gameList: MutableLiveData<List<Game>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getFromFirebase()
    }

    private fun getFromFirebase(){
        service.getGames(object: Callback<List<Game>> {
            override fun onSuccess(result: List<Game>?) {
                gameList.postValue(result)
                processFinished()
            }
            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished(){
        isLoading.value = true
    }
}