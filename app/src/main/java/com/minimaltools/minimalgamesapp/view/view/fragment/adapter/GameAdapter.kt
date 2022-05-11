package com.minimaltools.minimalgamesapp.view.view.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.minimaltools.minimalgamesapp.R
import com.minimaltools.minimalgamesapp.view.model.Game
import com.minimaltools.minimalgamesapp.view.view.fragment.adapter.listener.GameAdapterListener
import com.squareup.picasso.Picasso

class GameAdapter(private val listener: GameAdapterListener) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    var gameList = ArrayList<Game>()

    inner class GameViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    {
        val image: ImageView = view.findViewById(R.id.ivImage)
        val name: TextView = view.findViewById(R.id.txtName)
        val category: TextView = view.findViewById(R.id.txtCategory)
        val price: TextView = view.findViewById(R.id.txtPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    fun updateData(data:List<Game>)
    {
        gameList.clear()
        gameList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = gameList[position]
        Picasso.get().load(game.ImageUrl).into(holder.image)
        holder.name.text = game.Name
        holder.category.text = game.Category
        holder.price.text = "C$ " + game.Price.toString()
        holder.view.setOnClickListener { listener.onGameClick(game, position) }
    }
}