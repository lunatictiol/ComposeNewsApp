package com.lunatictiol.compsenewsapp.data.remote

import com.lunatictiol.compsenewsapp.data.remote.dto.ApiRespond
import retrofit2.http.GET
import retrofit2.http.Path

interface NYTimesAPI {


    @GET( value ="{section}")
    suspend fun getArticleList(
        @Path("section") section:String

    ): ApiRespond
}