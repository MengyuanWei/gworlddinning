package com.example.gworlddinning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// abstract class
class ReviewsAdapter(val reviews: List<Review>): RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>(){

    // RecyclerView needs a new XML layout for a new row.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        // TODO("not implemented")
        //To change body of created functions use File | Settings | File Templates.

        // Open and read the row_reviews.xml file and return a ReviewsViewHolder.
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_review, parent,false)

        return ReviewsViewHolder(view)
    }

    // How many rows (data items) the RecyclerView should render in total.
    override fun getItemCount(): Int {
        // TODO("not implemented")
        // To change body of created functions use File | Settings | File Templates.
        return reviews.size
    }

    // RecyclerView needs data to fill a new row,
    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        // TODO("not implemented")
        //To change body of created functions use File | Settings | File Templates.

        // Fill row at [position] with data.
        val currentReview = reviews[position]
        holder.name.text = currentReview.name
        holder.content.text = currentReview.content
        holder.ratingBar.rating = currentReview.ratingBar

    }

    class ReviewsViewHolder(view: View): RecyclerView.ViewHolder(view){
        // Constraint layout.
        val name: TextView = view.findViewById(R.id.name)
        val content: TextView = view.findViewById(R.id.review_content)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)

    }


}