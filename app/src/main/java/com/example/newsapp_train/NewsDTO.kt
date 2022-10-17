package com.example.newsapp_train

data class NewsDTO(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)