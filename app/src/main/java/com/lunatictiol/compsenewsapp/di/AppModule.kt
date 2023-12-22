package com.lunatictiol.compsenewsapp.di

import com.lunatictiol.compsenewsapp.BuildConfig
import com.lunatictiol.compsenewsapp.common.Constant
import com.lunatictiol.compsenewsapp.data.remote.NYTimesAPI
import com.lunatictiol.compsenewsapp.data.repository.ArticleRepositoryImpl
import com.lunatictiol.compsenewsapp.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNYTimesApi():NYTimesAPI{




        return Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL)
            .client(OkHttpClient().newBuilder().addInterceptor { apiKeyAsQuery(it) }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NYTimesAPI::class.java)
    }
    @Provides
    @Singleton
    fun providesArticleRepository(api: NYTimesAPI):ArticleRepository{
        return ArticleRepositoryImpl(api)
    }

    private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
        chain.request()
            .newBuilder()
            .url(chain.request().url.newBuilder().addQueryParameter("api-key",BuildConfig.API_KEY).build())
            .build()
    )
}



