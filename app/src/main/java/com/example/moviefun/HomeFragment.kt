package com.example.moviefun
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment(){
    private lateinit var recyclerViews: Array<RecyclerView>
    private lateinit var adapters: Array<MovieAdapter>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerViews = Array(5) { index ->
            view.findViewById<RecyclerView>(
                resources.getIdentifier(
                    "recyclerView$index",
                    "id",
                    requireContext().packageName
                )
            ).apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }
        adapters = Array(5) { index ->
            val movies = MovieParser.parseJson(requireContext(), "movie_list$index.json")
            val adapter = MovieAdapter(movies, object : MovieAdapter.OnItemClickListener {
                override fun onItemClick(movie: Movie) {
                    // Handle item click here
                    val intent = Intent(requireContext(), DetailsActivity::class.java).apply {
                        putExtra("movie", movie)
                    }
                    startActivity(intent)
                }
            }, index)
            adapter
        }
        recyclerViews.forEachIndexed { index, recyclerView ->
            recyclerView.adapter = adapters[index]
        }
        return view
    }
}
