package com.example.moviefun

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(
    private val movies: List<Movie>,
    private val listener: OnItemClickListener,
    private val index: Int
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutId = if (index == 0) {
            R.layout.item_full_width
        } else {
            R.layout.item
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val imdb:TextView=itemView.findViewById(R.id.rating)
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            imdb.text=movie.imdbRating
            Glide.with(itemView.context)
                .load(movie.imageName)
                .into(imageView)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val movie = movies[position]
                listener.onItemClick(movie)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(movie: Movie)
    }
}
