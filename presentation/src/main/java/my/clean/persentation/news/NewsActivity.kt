package my.clean.persentation.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.news_articles.*
import my.clean.presentation.R
import my.clean.presentation.entities.Status
import org.koin.android.viewmodel.ext.android.viewModel

class NewsActivity : AppCompatActivity() {

    private val newsList: NewsViewModel by viewModel()
    private lateinit var listAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_articles)
        listAdapter = NewsListAdapter()

        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = listAdapter
        newsList.fetchNews()
    }

    override fun onStart() {
        super.onStart()
        newsList.getNewsLiveData().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {
                    //Error handling
                }
                Status.LOADING -> {
                    //Progress
                }
                Status.SUCCESSFUL -> {
                    // On Successful response
                }
            }
            it?.data?.let { response ->
                listAdapter.updateList(response.articles)
            }
        })
    }
}