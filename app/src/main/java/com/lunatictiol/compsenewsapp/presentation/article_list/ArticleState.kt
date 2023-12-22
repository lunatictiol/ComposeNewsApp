package com.lunatictiol.compsenewsapp.presentation.article_list

import com.lunatictiol.compsenewsapp.domain.model.Article


data class ArticleState(
    val isLoading : Boolean = false,
    val articleList: List<Article> = emptyList(),
    val error:String = "",
    var expandArticleBox: Boolean = false,
    var section:String="home"


)
