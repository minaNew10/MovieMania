package com.example.moviemania.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.moviemania.databinding.FragmentMainBinding
import com.example.moviemania.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(){
    private val moviesViewModel by viewModels<MoviesViewModel>()
    private lateinit var  toast : Toast
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toast = Toast.makeText(requireActivity(),"",Toast.LENGTH_LONG)

        val binding = FragmentMainBinding.inflate(layoutInflater)
        val moviesAdapter = MoviesAdapter(OnImageClickHandler {
            toast.setText(it)
            toast.show()
        })
        val manager = GridLayoutManager(activity,2)
        manager.spanSizeLookup = object  : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int) = when (position){
                0 -> 2
                else -> 1
            }
        }
        val snapHelper = LinearSnapHelper()
        binding.apply {
            this.layoutViewmodel = moviesViewModel
            setLifecycleOwner(viewLifecycleOwner)
            moviesRecyclerView.apply {
                adapter = moviesAdapter
                this.layoutManager = manager
                snapHelper.attachToRecyclerView(this)
                setHasFixedSize(true)
            }

        }

        return binding.root
    }
}