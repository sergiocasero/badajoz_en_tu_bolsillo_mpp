package com.badajoz.badajozentubolsillo.repository

import com.badajoz.badajozentubolsillo.datasource.NewsNetworkDataSource
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.category.news.NewsDetail
import com.badajoz.badajozentubolsillo.model.category.news.NewsPage

interface NewsRepository {
    suspend fun getNewsPage(page: Int): Either<AppError, NewsPage>

    suspend fun getNewsDetail(link: String): Either<AppError, NewsDetail>
}

class SharedNewsRepository(private val network: NewsNetworkDataSource) : NewsRepository {
    override suspend fun getNewsPage(page: Int): Either<AppError, NewsPage> =
        network.getNewsPage(page)

    override suspend fun getNewsDetail(link: String): Either<AppError, NewsDetail> =
        network.getNewsDetail(link)
}