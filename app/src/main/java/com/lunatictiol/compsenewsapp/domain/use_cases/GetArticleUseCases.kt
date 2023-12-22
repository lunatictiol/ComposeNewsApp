package com.lunatictiol.compsenewsapp.domain.use_cases

import com.lunatictiol.compsenewsapp.common.Resource
import com.lunatictiol.compsenewsapp.data.remote.dto.toArticle
import com.lunatictiol.compsenewsapp.domain.model.Article
import com.lunatictiol.compsenewsapp.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetArticleUseCases @Inject constructor(
    private val repository: ArticleRepository
) {
    operator fun invoke(section:String) : Flow<Resource<List<Article>>> = flow {
        try {
                 emit(Resource.Loading())
            val articles = repository.getArticles(section)
            emit(Resource.Success(articles))

        }
        catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage?: "Error connecting to network"))
        }
        catch (e: IOException){
            emit(Resource.Error(e.localizedMessage?: "Couldn't connect to the server "))
        }
    }
}
