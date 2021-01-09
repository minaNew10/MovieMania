package com.example.moviemania.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviemania.databinding.FragmentMainBinding
import com.example.moviemania.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainFragment : Fragment(){
    private val moviesViewModel by viewModels<MoviesViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(layoutInflater)
        val moviesAdapter = MoviesAdapter()
        binding.apply {
            this.layoutViewmodel = moviesViewModel
            setLifecycleOwner(viewLifecycleOwner)
            moviesRecyclerView.apply {
                adapter = moviesAdapter
                this.layoutManager = GridLayoutManager(requireContext(),2)
                setHasFixedSize(true)
            }

        }

        return binding.root
    }
}