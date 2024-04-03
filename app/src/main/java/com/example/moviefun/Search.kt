package com.example.moviefun

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Search : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter2
    private lateinit var searchEditText: EditText
    private var movieList = mutableListOf<Movie>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        recyclerView = view.findViewById(R.id.searchRecyclerView)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        movieList = MovieParser.parseJson(requireContext(), "movie_list1.json").toMutableList()
        adapter = MovieAdapter2(movieList)
        recyclerView.adapter = adapter
        searchEditText=view.findViewById(R.id.searchEditText)
        searchEditText.addTextChangedListener{
            val searchText = it.toString()
            filterMovies(searchText)
        }
        return view
    }
    private fun filterMovies(query: String) {
        val filteredList = mutableListOf<Movie>()
        for (movie in movieList) {
            if (movie.title.contains(query, ignoreCase = true)) {
                filteredList.add(movie)
            }
        }
        // Update the adapter with the filtered list
        adapter.updateMovies(filteredList)
    }
}
