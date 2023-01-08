package com.badajoz.badajozentubolsillo.viewmodel

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.news.NewsDetail
import com.badajoz.badajozentubolsillo.repository.NewsRepository
import com.badajoz.badajozentubolsillo.utils.exhaustive
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class NewsDetailViewModel(private val link: String, initialState: NewsDetailState) :
    RootViewModel<NewsDetailState, NewsDetailEvent, NewsDetailActions>(initialState) {

    private val repository: NewsRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = NewsDetailState.InProgress

            execute { repository.getNewsDetail(link) }.fold(
                error = { println("Error: $it") },
                success = {
                    println("Success: $it")
                    _uiState.value = NewsDetailState.Success(it)
                }
            )
        }
    }

    override fun onEvent(event: NewsDetailEvent) {
        when (event) {
            NewsDetailEvent.Attach -> attach()
        }.exhaustive
    }
}

sealed class NewsDetailState : ViewState() {
    object InProgress : NewsDetailState()
    class Error(val error: AppError) : NewsDetailState()
    data class Success(val newsDetail: NewsDetail) : NewsDetailState()
}

sealed class NewsDetailEvent {
    object Attach : NewsDetailEvent()
}

sealed class NewsDetailActions {

}