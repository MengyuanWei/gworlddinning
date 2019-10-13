package com.example.gworlddinning

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

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
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set the adapter.
        // recyclerView.adapter =
    }

    fun getFakeReviews(): List<Review>{
        return listOf(
                Review(name="Nia G.", content = "Great takeout food. Great prices and hella quick! This is my third order with May Island and the food was pretty good.", ratingBar = 3.5),
                Review(name = "Adianna P.", content = "My 2nd time ordering from May Island is great as always!", ratingBar = 4.0),
                Review(name = "Jason J.", content = "I want to say that my family really enjoy May Island.", ratingBar = 5.0)
        )
    }

}
