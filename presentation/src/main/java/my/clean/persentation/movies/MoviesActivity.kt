package my.clean.persentation.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movies.*
import my.clean.presentation.R
import my.clean.presentation.entities.Status
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

    private val movieList: MoviesViewModel by viewModel()
    private lateinit var listAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies)
        listAdapter = MoviesListAdapter()

        list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        list.adapter = listAdapter

        movieList.fetchMovies()
    }

    override fun onStart() {
        super.onStart()
        movieList.getMoviesLiveData().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
                Status.SUCCESSFUL -> {

                }
            }
            it?.data?.let { movies ->
                listAdapter.updateList(movies)
            }
        })

    }


}


