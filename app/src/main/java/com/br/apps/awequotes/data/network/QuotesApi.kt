package com.br.apps.awequotes.data.network

import com.br.apps.awequotes.data.network.model.QuotesResponse
import com.br.apps.awequotes.data.network.model.QuotesResult
import retrofit2.Call
import retrofit2.http.GET

interface QuotesApi {
    @GET("/quotes")
    fun getAllQuotes(): Call<QuotesResponse>

    @GET("/random")
    fun getRandomQuotes(): Call<QuotesResult>
}