package com.example.moviefun

import MovieParser
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), MovieAdapter.OnItemClickListener {
    private lateinit var recyclerViews: Array<RecyclerView>
    private lateinit var adapters: Array<MovieAdapter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViews = Array(5) { index ->
            findViewById<RecyclerView>(resources.getIdentifier("recyclerView$index", "id", packageName)).apply {
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            }
        }
        adapters = Array(5) { index ->
            val movies = MovieParser.parseJson(this, "movie_list$index.json") // Adjust file names as needed
            MovieAdapter(movies, this@MainActivity,index)
        }

        // Set adapters to RecyclerViews
        recyclerViews.forEachIndexed { index, recyclerView ->
            recyclerView.adapter = adapters[index]
        }
    }

    override fun onItemClick(movie: Movie) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("movie", movie)
        }
        startActivity(intent)
    }
}
