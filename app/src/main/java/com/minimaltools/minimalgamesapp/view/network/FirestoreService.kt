package com.minimaltools.minimalgamesapp.view.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.minimaltools.minimalgamesapp.view.model.Event
import com.minimaltools.minimalgamesapp.view.model.Game
import com.minimaltools.minimalgamesapp.view.model.VideoGameCompany

const val GAME_COLLECTION_NAME="Game"
const val EVENT_COLLECTION_NAME="Event"
const val VIDEO_GAME_COMPANY_COLLECTION_NAME="VideoGameCompany"

class FirestoreService {
    val firestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init{
        firestore.firestoreSettings=settings
    }

    fun getGames(callback: Callback <List<Game>>) {
        firestore.collection(GAME_COLLECTION_NAME)
            .orderBy("Name")
            .get()
            .addOnSuccessListener { result ->
                for(doc in result) {
                    val list=result.toObjects(Game::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun getEvents(callback:Callback <List<Event>>) {
        firestore.collection(EVENT_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for(doc in result) {
                    val list=result.toObjects(Event::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun getVideoGamesCompany(callback:Callback <List<VideoGameCompany>>) {
        firestore.collection(VIDEO_GAME_COMPANY_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for(doc in result) {
                    val list=result.toObjects(VideoGameCompany::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
}