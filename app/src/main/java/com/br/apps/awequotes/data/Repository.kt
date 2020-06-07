package com.br.apps.awequotes.data

import androidx.lifecycle.LiveData
import com.br.apps.awequotes.data.db.model.Quotes

interface Repository {

    suspend fun getAllFavQuotes(): LiveData<List<Quotes>>

    suspend fun addToFavorites(quotes: Quotes): Int

    suspend fun addRandomQuoteToDb(randomQuote: Quotes)

    suspend fun removeRandomQuoteFromDb(randomQuote: Quotes)

    suspend fun deleteAll()

    suspend fun getAllQuotesFromNetwork(): LiveData<List<Quotes>>

    suspend fun getRandomQuoteFromNetwork(): Quotes?
}