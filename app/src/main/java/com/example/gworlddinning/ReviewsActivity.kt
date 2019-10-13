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
        recyclerView.adapter = ReviewsAdapter(getFakeReviews())
    }

    fun getFakeReviews(): List<Review>{
        return listOf(
                Review(name = "Sheila J.", content = "I walked in on a Sunday at 1:30pm and the place was jam packed, we had about a 15 minute wait. " +
                        "We visited this place based on reviews only, so were really looking forward to it as our sudden urge for sushi kicked in on a lazy Sunday. " +
                        "This is top shelf sushi-  It's fresh fish - with or without rice.  Both the sushi and sashimi are mouth watering, and the eel sauces add that extra kick, " +
                        "like the perfect bow wrapped on a beautifully wrapped gift. Don't be fooled by other surrounding sushi places that claim originality, " +
                        "this is the place to go. The sushi chefs here are artists, masters of the art of sushi. If it wasn't for the wait, I would give it 5 stars.",
                        ratingBar = 4.5f),
                Review(name="Nia G.", content = "Great takeout food. Great prices and hella quick! This is my third order with May Island and the food was pretty good." +
                        "Finding sushi in the DC area is easy. Finding good sushi is harder. Zento might the the best. " +
                        "It gets all these good ratings from everyone. This made me skeptical and naturally I wanted to check it out for myself.",
                        ratingBar = 3.5f),
                Review(name = "Adianna P.", content = "My 2nd time ordering from May Island is great as always! Sushi and meal was decent.  " +
                        "Hibachi steak was decent for the price and cooked well- the fried rice was the highlight and was actually similar to an actual hibachi restaurant.  " +
                        "Salad and miso was good - sushi pieces was just ok as the salmon still had some of the skin on it.",
                        ratingBar = 4.0f),
                Review(name = "Jason J.", content = "I want to say that my family really enjoy May Island. Quite good! The sashimi tasted very fresh. " +
                        "The ramen had good flavor. The restaurant was very clean and the staff is very friendly! " +
                        "I just find it a tad overpriced but I think their happy hour is a good deal! I will definitely be a returning customer.",
                        ratingBar = 5.0f),
                Review(name = "Nick C", content = "I guess I really like May Island and thank you so much for giving us delicious dishes! " +
                        "The food- ordered 3 different rolls (don't remember what they were called), and all of them were equally saucey. " +
                        "There was a good amount of fish in each of them though, but it was just overpowered by all the other ingredients, " +
                        "hence the 3 stars. It didn't fill my sushi craving, as I like to taste the freshness of what I'm eating and different flavors. " +
                        "Also ordered the kara-age chicken, which was surprisingly really good. " +
                        "The batter is very light and gives it just enough crunch without drowning the chicken in breading. The chicken itself was also very moist.",
                        ratingBar = 4.5f)

        )
    }

}
