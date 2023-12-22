package com.lunatictiol.compsenewsapp.domain.model

import com.lunatictiol.compsenewsapp.data.remote.dto.MultimediaX

data class Article(
    val abstract: String,
    val byline: String,
    val published_date: String,
    val section: String,
    val title: String,
    val url: String,
    val multimedia: List<MultimediaX>? = null
)
