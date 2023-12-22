package com.lunatictiol.compsenewsapp.domain.repository


import com.lunatictiol.compsenewsapp.domain.model.Article


interface ArticleRepository {

    suspend fun getArticles(section: String): List<Article>
}