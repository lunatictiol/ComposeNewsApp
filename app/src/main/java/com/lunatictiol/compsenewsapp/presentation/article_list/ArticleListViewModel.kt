package com.lunatictiol.compsenewsapp.presentation.article_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lunatictiol.compsenewsapp.common.Resource
import com.lunatictiol.compsenewsapp.domain.use_cases.GetArticleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
val getArticleUseCases: GetArticleUseCases,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _state = mutableStateOf(ArticleState())
    val state: State<ArticleState> = _state


    init {

        getArticles(state.value.section)
    }

    private fun getArticles(section:String) {

        getArticleUseCases(section).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ArticleState(articleList = result.data ?: emptyList()
                    , section = section
                        )
                }

                is Resource.Error -> {
                    _state.value = ArticleState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = ArticleState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)


    }
}