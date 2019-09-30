package my.clean.persentation.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import my.clean.domian.common.Mapper
import my.clean.domian.entities.NewsSourcesEntity
import my.clean.domian.usecases.GetNewsUseCase
import my.clean.persentation.common.BaseViewModel
import my.clean.presentation.entities.Data
import my.clean.presentation.entities.Error
import my.clean.presentation.entities.NewsSources
import my.clean.presentation.entities.Status

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase,
    private val mapper: Mapper<NewsSourcesEntity, NewsSources>
) : BaseViewModel() {

    companion object {
        private val TAG = "ViewModel"
    }

    var mNews = MutableLiveData<Data<NewsSources>>()

    fun fetchNews() {
        val disposable = getNewsUseCase.getNews()
            .flatMap { mapper.flowable(it) }
            .subscribe({ response ->
                Log.d(TAG, "On Next Called")
                mNews.value = Data(responseType = Status.SUCCESSFUL, data = response)
            }, { error ->
                Log.d(TAG, "On Error Called")
                mNews.value = Data(responseType = Status.ERROR, error = Error(error.message))
            }, {
                Log.d(TAG, "On Complete Called")
            })

        addDisposable(disposable)
    }

    fun getNewsLiveData() = mNews

}