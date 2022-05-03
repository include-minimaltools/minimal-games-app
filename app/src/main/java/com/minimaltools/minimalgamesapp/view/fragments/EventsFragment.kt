package com.minimaltools.minimalgamesapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.minimaltools.minimalgamesapp.R

class EventsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_events, container, false)
}