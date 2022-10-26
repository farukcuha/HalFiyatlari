package com.pandorina.hal_fiyatlar.presentation.screens.news

import com.pandorina.hal_fiyatlar.domain.model.news.Category
import com.pandorina.hal_fiyatlar.domain.model.news.News
import com.pandorina.hal_fiyatlar.core.BaseUiState

data class NewsUiState(
    var categories: List<Category>? = null,
    var news: List<News>? = null,
    override var isLoading: Boolean = false,
    override var error: String? = null
): BaseUiState()
