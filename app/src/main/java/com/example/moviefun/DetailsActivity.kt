package com.example.moviefun

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Retrieve the Movie object passed from MainActivity
        val movie = intent.getSerializableExtra("movie") as? Movie

        val titleTextView:TextView=findViewById(R.id.titleTextView)
        val descriptionTextView:TextView=findViewById(R.id.description)
        val imageView:ImageView=findViewById(R.id.imageView)
        val relaseDate:TextView=findViewById(R.id.ReleaseDate)
        val director:TextView=findViewById(R.id.director)
        val starCast:TextView=findViewById(R.id.starCast)
        val rating:TextView=findViewById(R.id.Rating)
        movie?.let {
            relaseDate.text=it.releaseDate
            titleTextView.text = it.title
            descriptionTextView.text = it.description
            director.text=it.director
            rating.text=it.imdbRating
            val stars=it.starCast.joinToString(", ")
            starCast.text=stars
            Glide.with(this)
                .load(it.imageName)
                .into(imageView)
        }
    }
}
