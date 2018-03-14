package com.jorpaspr.movies.main.mvp.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jorpaspr.movies.R
import com.jorpaspr.movies.database.UserMovie
import com.squareup.picasso.Picasso
import java.util.*

class MoviesAdapter(private val mContext: Context, private val mListener: OnMovieClicked) : RecyclerView.Adapter<MoviesAdapter.Companion.MoviesViewHolder>() {

    companion object {

        class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val mTitle: TextView = view.findViewById(R.id.title)
            val plot: TextView = view.findViewById(R.id.plot)
            val image: ImageView = view.findViewById(R.id.image)
            val bookmarked: ImageView = view.findViewById(R.id.bookmarked)
        }
    }

    private var userMovies: List<UserMovie> = ArrayList()

    fun setData(data: List<UserMovie>) {
        userMovies = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.Companion.MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = userMovies[position]
        holder.mTitle.text = item.title
        holder.plot.text = item.plot
        Picasso.with(mContext).load(item.poster).fit().centerInside().into(holder.image)
        holder.itemView.setOnClickListener { _ -> mListener.onClick(item) }
        holder.bookmarked.visibility = if (item.bookmarked) VISIBLE else GONE
        holder.bookmarked.setColorFilter(R.color.colorBookmarked)
    }

    override fun getItemCount(): Int {
        return userMovies.size
    }

    // TODO: Replace with RxView.clicks()
    interface OnMovieClicked {
        fun onClick(userMovie: UserMovie)
    }
}
