package com.br.apps.awequotes.data.network

import com.br.apps.awequotes.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuotesService() {

    private lateinit var quotesApi: QuotesApi

    init {
        val mRetrofit: Retrofit =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.SERVER_URL).build()
        quotesApi = mRetrofit.create(QuotesApi::class.java)
    }

    companion object {
        public var quotesService: QuotesService = QuotesService()
    }

    fun getQuotesApi() = quotesApi
}