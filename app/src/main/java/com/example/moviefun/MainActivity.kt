package com.example.moviefun

import MovieParser
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(){
    lateinit var bottomnav:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(HomeFragment());
        bottomnav=findViewById(R.id.bottom_navigation)
        bottomnav.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    loadFragment(HomeFragment())
                    true
                }   
                R.id.action_search -> {
                    loadFragment(Search())
                    true
                }
                R.id.action_fav -> {
                    loadFragment(Favourites())
                    true
                }
                else -> false
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

}
