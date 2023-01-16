package com.badajoz.badajozentubolsillo.viewmodel.news

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.news.News
import com.badajoz.badajozentubolsillo.repository.NewsRepository
import com.badajoz.badajozentubolsillo.viewmodel.RootViewModel
import com.badajoz.badajozentubolsillo.viewmodel.ViewState
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class NewsViewModel(initialState: HomeState) :
    RootViewModel<HomeState, NewsEvent>(initialState) {

    private val repository: NewsRepository by inject()

    private var nextPage = 0

    private val newsToShow = mutableListOf<News>()

    override fun attach() = apply {
        loadPage(nextPage)
    }

    private fun loadPage(page: Int, initialState: HomeState = HomeState.InProgress) {
        vmScope.launch {
            _uiState.value = initialState

            execute { repository.getNewsPage(page) }.fold(
                error = { _uiState.value = HomeState.Error(it) },
                success = {
                    nextPage = it.next
                    newsToShow.addAll(it.news)
                    _uiState.value = HomeState.Success(newsToShow.toList(), loadingMore = false)
                }
            )
        }
    }

    override fun onEvent(event: NewsEvent) {
        when (event) {
            NewsEvent.Attach -> attach()
            NewsEvent.OnLoadMore -> loadPage(
                nextPage,
                HomeState.Success(news = newsToShow.toList(), loadingMore = true)
            )
        }
    }
}

sealed class HomeState : ViewState() {
    object InProgress : HomeState()
    class Error(val error: AppError) : HomeState()
    data class Success(val news: List<News>, val loadingMore: Boolean) : HomeState()

}

sealed class NewsEvent {
    object Attach : NewsEvent()
    object OnLoadMore : NewsEvent()
}
