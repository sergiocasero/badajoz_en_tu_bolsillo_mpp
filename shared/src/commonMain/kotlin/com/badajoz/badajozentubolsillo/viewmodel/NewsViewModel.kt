package com.badajoz.badajozentubolsillo.viewmodel

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.news.News
import com.badajoz.badajozentubolsillo.repository.NewsRepository
import com.badajoz.badajozentubolsillo.utils.exhaustive
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

    private fun loadPage(page: Int) {
        vmScope.launch {
            _uiState.value = HomeState.InProgress

            execute { repository.getNewsPage(page) }.fold(
                error = { println("Error: $it") },
                success = {
                    nextPage = it.next
                    newsToShow.addAll(it.news)
                    _uiState.value = HomeState.Success(newsToShow.toList())
                }
            )
        }
    }

    override fun onEvent(event: NewsEvent) {
        when (event) {
            NewsEvent.Attach -> attach()
            NewsEvent.OnLoadMore -> loadPage(nextPage)
        }.exhaustive
    }
}

sealed class HomeState : ViewState() {
    object InProgress : HomeState()
    class Error(val error: AppError) : HomeState()
    data class Success(val news: List<News>) : HomeState()
}

sealed class NewsEvent {
    object Attach : NewsEvent()
    object OnLoadMore : NewsEvent()
}
