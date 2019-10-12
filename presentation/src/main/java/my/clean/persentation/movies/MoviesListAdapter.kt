package my.clean.persentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_item.view.*
import my.clean.persentation.entities.Movie
import my.clean.persentation.movies.MoviesListAdapter.MovieViewHolder
import my.clean.presentation.R

class MoviesListAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    var movies = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            with(itemView) {
                heading.text = movie.title
            }
        }
    }

    fun updateList(list: List<Movie>) {
        if (list.isNotEmpty()) {
            movies.clear()
            movies.addAll(list)
            notifyDataSetChanged()
        }
    }
}