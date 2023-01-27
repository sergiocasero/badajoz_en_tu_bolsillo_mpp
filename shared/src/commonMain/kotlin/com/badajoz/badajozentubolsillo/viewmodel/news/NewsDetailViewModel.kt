package com.badajoz.badajozentubolsillo.viewmodel.news

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.news.NewsDetail
import com.badajoz.badajozentubolsillo.repository.NewsRepository
import com.badajoz.badajozentubolsillo.utils.exhaustive
import com.badajoz.badajozentubolsillo.viewmodel.RootViewModel
import com.badajoz.badajozentubolsillo.viewmodel.ViewEvent
import com.badajoz.badajozentubolsillo.viewmodel.ViewState
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class NewsDetailViewModel(private val link: String, initialState: NewsDetailState) :
    RootViewModel<NewsDetailState, NewsDetailEvent>(initialState) {

    private val repository: NewsRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = NewsDetailState.InProgress

            execute { repository.getNewsDetail(link) }.fold(
                error = { _uiState.value = NewsDetailState.Error(it) },
                success = {
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

sealed class NewsDetailEvent : ViewEvent() {
    object Attach : NewsDetailEvent()
}
