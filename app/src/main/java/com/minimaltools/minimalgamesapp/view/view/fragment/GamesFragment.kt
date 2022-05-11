package com.minimaltools.minimalgamesapp.view.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minimaltools.minimalgamesapp.R
import com.minimaltools.minimalgamesapp.databinding.FragmentGamesBinding
import com.minimaltools.minimalgamesapp.view.model.Game
import com.minimaltools.minimalgamesapp.view.view.fragment.adapter.GameAdapter
import com.minimaltools.minimalgamesapp.view.view.fragment.adapter.listener.GameAdapterListener
import com.minimaltools.minimalgamesapp.view.viewmodel.GameViewModel

class GamesFragment : Fragment(), GameAdapterListener {
    private lateinit var viewModel: GameViewModel
    private lateinit var adapter: GameAdapter

    private var fbinding : FragmentGamesBinding? = null
    private val binding get() = fbinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fbinding = FragmentGamesBinding.inflate(layoutInflater)
        val view = binding.root

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            GameViewModel::class.java)
        viewModel.refresh()
        this.adapter = GameAdapter(this)

        binding.rvGames.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = this.adapter
        }

        observeViewModel()

        return view
    }

    private fun observeViewModel() {
        viewModel.gameList.observe(viewLifecycleOwner, Observer<List<Game>> { games -> this.adapter.updateData(games) })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fbinding = null
    }

    override fun onGameClick(game: Game, position: Int) {
        TODO("Not yet implemented")
    }
}