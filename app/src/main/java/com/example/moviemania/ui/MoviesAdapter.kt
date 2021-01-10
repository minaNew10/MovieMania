package com.example.moviemania.ui


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemania.R
import com.example.moviemania.databinding.ItemMovieBinding
import com.example.moviemania.domain.Movie

private const val TAG = "MoviesAdapter"
private const val ITEM_VIEW_MOVIE = 1
private const val ITEM_VIEW_HEADER = 0
class MoviesAdapter(val clickHandler: OnImageClickHandler) : ListAdapter<DataItem, RecyclerView.ViewHolder>(DiffUtilCallBack) {
    companion object DiffUtilCallBack : DiffUtil.ItemCallback<DataItem>(){
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ITEM_VIEW_HEADER -> HeaderViewHolder.from(parent)
            else-> MovieViewHolder.from(parent)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
           is MovieViewHolder -> {
               val dataItem = getItem(position) as DataItem.MovieItem
               holder.bind(dataItem.movie,clickHandler)
           }
       }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is DataItem.MovieItem -> ITEM_VIEW_MOVIE
            is DataItem.HeaderItem -> ITEM_VIEW_HEADER
        }

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
    class HeaderViewHolder(val view :View): RecyclerView.ViewHolder(view){
        companion object{
            fun from(parent: ViewGroup) : HeaderViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.header,parent,false)
                return HeaderViewHolder(view)
            }
        }
    }


}
class OnImageClickHandler(val clickListener: (String) -> Unit){
    fun onClick(movie: Movie)  = clickListener(movie.title)
}
sealed class DataItem{
    abstract val id: Long
    class MovieItem(val movie: Movie) : DataItem(){
        override val id: Long
            get() = movie.id as Long

    }
    object HeaderItem : DataItem(){
        override val id: Long
            get() = Long.MIN_VALUE

    }
}