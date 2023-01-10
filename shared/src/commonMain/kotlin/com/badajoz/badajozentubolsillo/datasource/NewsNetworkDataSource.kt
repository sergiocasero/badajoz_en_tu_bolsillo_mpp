package com.badajoz.badajozentubolsillo.datasource

import com.badajoz.badajozentubolsillo.model.AppError
import com.badajoz.badajozentubolsillo.model.Either
import com.badajoz.badajozentubolsillo.model.category.news.NewsDetail
import com.badajoz.badajozentubolsillo.model.category.news.NewsPage
import com.badajoz.badajozentubolsillo.model.request.EncryptedNetworkRequest
import com.badajoz.badajozentubolsillo.model.request.NewsDetailRequest
import com.badajoz.badajozentubolsillo.model.response.EncryptedNetworkResponse
import com.badajoz.badajozentubolsillo.utils.BASE_URL
import com.badajoz.badajozentubolsillo.utils.BuildType
import com.badajoz.badajozentubolsillo.utils.decrypt
import com.badajoz.badajozentubolsillo.utils.encrypt
import com.badajoz.badajozentubolsillo.utils.withPath
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.utils.io.core.use

interface NewsNetworkDataSource : NetworkDataSource {
    suspend fun getNewsPage(page: Int): Either<AppError, NewsPage>
    suspend fun getNewsDetail(link: String): Either<AppError, NewsDetail>
}

class SharedNewsNetworkDataSource(private val buildType: BuildType) : NewsNetworkDataSource {
    override suspend fun getNewsPage(page: Int): Either<AppError, NewsPage> = execute {
        buildClientWithAuth(BASE_URL, buildType).use {
            it.get {
                url.withPath(Uris.News.page(page))
            }.body<EncryptedNetworkResponse>().result.decrypt()
        }
    }

    override suspend fun getNewsDetail(link: String): Either<AppError, NewsDetail> = execute {
        buildClientWithAuth(BASE_URL, buildType).use {
            it.post {
                url.withPath(Uris.News.detail)
                setBody(EncryptedNetworkRequest(NewsDetailRequest(link).encrypt()))
            }.body<EncryptedNetworkResponse>().result.decrypt()
        }
    }

}