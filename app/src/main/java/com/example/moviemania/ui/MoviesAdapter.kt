package com.example.moviemania.ui


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemania.databinding.ItemMovieBinding
import com.example.moviemania.domain.Movie

private const val TAG = "MoviesAdapter"
class MoviesAdapter(val clickHandler: OnImageClickHandler) : ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(DiffUtilCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        Log.i(TAG, "onBindViewHolder: $movie" )
        holder.bind(movie,clickHandler)
    }

    class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup): MovieViewHolder{
                val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return MovieViewHolder(binding)
            }
        }
        fun bind(movie: Movie, clickHandler: OnImageClickHandler){
            binding.apply {
                 this.movie = movie
                this.imageClickHandler = clickHandler
                executePendingBindings()
            }

        }
    }

    companion object DiffUtilCallBack : DiffUtil.ItemCallback<Movie>(){
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}
class OnImageClickHandler(val clickListener: (String) -> Unit){
    fun onClick(movie: Movie)  = clickListener(movie.title)
}