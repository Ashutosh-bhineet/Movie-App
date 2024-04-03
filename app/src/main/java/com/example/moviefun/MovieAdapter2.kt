package com.example.moviefun

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter2(private var movieList: List<Movie>) : RecyclerView.Adapter<MovieAdapter2.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val imdb:TextView=itemView.findViewById(R.id.rating)

        fun bind(movie: Movie) {
            imdb.text=movie.imdbRating
            Glide.with(itemView.context)
                .load(movie.imageName)
                .into(imageView)
        }
    }
    fun updateMovies(newMovies: List<Movie>) {
        movieList = newMovies
        notifyDataSetChanged()
    }
}
