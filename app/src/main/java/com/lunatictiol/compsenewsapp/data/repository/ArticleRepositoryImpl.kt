package com.lunatictiol.compsenewsapp.data.repository

import com.lunatictiol.compsenewsapp.data.remote.NYTimesAPI
import com.lunatictiol.compsenewsapp.data.remote.dto.toArticle

import com.lunatictiol.compsenewsapp.domain.model.Article
import com.lunatictiol.compsenewsapp.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val api:NYTimesAPI
):ArticleRepository {
    override suspend fun getArticles(section: String): List<Article> {
        return api.getArticleList(section).results.map{it.toArticle() }
    }

}