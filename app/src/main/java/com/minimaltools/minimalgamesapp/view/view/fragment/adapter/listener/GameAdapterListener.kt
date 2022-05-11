package com.minimaltools.minimalgamesapp.view.view.fragment.adapter.listener

import com.minimaltools.minimalgamesapp.view.model.Game

interface GameAdapterListener {
    fun onGameClick(game: Game, position: Int)
}