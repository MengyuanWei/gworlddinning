package com.example.gworlddinning

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.RecyclerView

class ReviewsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)

        val location: String = intent.getStringExtra("LOCATION")

        title = getString(R.string.reviews_title, location)
//        title = "Reviews near $location"

        recyclerView = findViewById(R.id.recyclerView)
    }

}
