package com.badajoz.badajozentubolsillo.viewmodel

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.news.NewsPage
import com.badajoz.badajozentubolsillo.repository.NewsRepository
import com.badajoz.badajozentubolsillo.utils.exhaustive
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class HomeViewModel(initialState: HomeState) : RootViewModel<HomeState, HomeEvent, HomeActions>(initialState) {

    private val repository: NewsRepository by inject()

    override fun attach() = apply {
        vmScope.launch {
            _uiState.value = HomeState.InProgress

            execute { repository.getNewsPage(0) }.fold(
                error = { println("Error: $it") },
                success = { _uiState.value = HomeState.Success(it) }
            )
        }
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.Attach -> attach()
        }.exhaustive
    }
}

sealed class HomeState : ViewState() {
    object InProgress : HomeState()
    class Error(val error: AppError) : HomeState()
    data class Success(val page: NewsPage) : HomeState()
}

sealed class HomeEvent {
    object Attach : HomeEvent()
}

sealed class HomeActions {

}