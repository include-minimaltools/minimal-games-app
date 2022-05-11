package com.minimaltools.minimalgamesapp.view.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.minimaltools.minimalgamesapp.R
import com.minimaltools.minimalgamesapp.view.model.Event
import com.minimaltools.minimalgamesapp.view.model.Game
import com.minimaltools.minimalgamesapp.view.network.Callback
import com.minimaltools.minimalgamesapp.view.network.FirestoreService
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MinimalGamesApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menu: BottomNavigationView = findViewById(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(
            menu,
            Navigation.findNavController(this, R.id.fragment_container)
        )

        var service = FirestoreService()
        println("Getting games\n\n\n\n\n\n\n\n")
        service.getGames(object: Callback<List<Game>> {
            override fun onSuccess(result: List<Game>?) {
                println(result)
            }
            override fun onFailed(exception: Exception) {
                println(exception)
            }
        })

//        var gameJSON = JSONArray("[\n" +
//                "    {\n" +
//                "        'Name' : 'Need for Speed: Most Wanted',\n" +
//                "        'Category' : 'Racing',\n" +
//                "        'Price' : 1.60,\n" +
//                "        'ImageUrl' : 'https://as01.epimg.net/futbol/imagenes/2022/04/26/champions/1650948733_580917_1651005910_noticia_normal_recorte1.jpg'\n" +
//                "    },\n" +
//                "    {\n" +
//                "        'Name' : 'GTA V',\n" +
//                "        'Category' : 'Action',\n" +
//                "        'Price' : 1.60,\n" +
//                "        'ImageUrl' : 'https://as01.epimg.net/futbol/imagenes/2022/04/26/champions/1650948733_580917_1651005910_noticia_normal_recorte1.jpg'\n" +
//                "    }\n" +
//                "]")
//
//        val fireDB: FirebaseFirestore = FirebaseFirestore.getInstance()
//        for (i in 0 until gameJSON.length()) {
//            val gameObj = gameJSON.get(i) as JSONObject
//            var game = Game()
//
//            game.Name = gameObj.getString("Name")
//            game.Category = gameObj.getString("Category")
//            game.Price = gameObj.getDouble("Price")
//            game.ImageUrl = gameObj.getString("ImageUrl")
//
//            fireDB.collection("Game").document().set(game)
//        }
    }
}