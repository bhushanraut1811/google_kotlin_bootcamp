package com.br.apps.awequotes.data

import androidx.lifecycle.LiveData
import com.br.apps.awequotes.data.db.QuotesDao
import com.br.apps.awequotes.data.db.model.Quotes
import com.br.apps.awequotes.data.network.QuotesApi

class RepositoryImpl(private val dao: QuotesDao, private val api: QuotesApi) : Repository {

    private val TAG: String? = RepositoryImpl::class.simpleName

    override suspend fun getAllFavQuotes(): LiveData<List<Quotes>> =
        dao.getAllFavoriteQuotes(true)

    override suspend fun addToFavorites(quotes: Quotes): Int =
        dao.updateFavorite(!quotes.isFav, quotes.id)

    override suspend fun addRandomQuoteToDb(randomQuote: Quotes) {
        randomQuote.isFav = true
        dao.insertQuote(randomQuote)
    }

    override suspend fun removeRandomQuoteFromDb(randomQuote: Quotes) {
        dao.deleteRandomQuote(randomQuote)
    }

    override suspend fun deleteAll() = dao.deleteAllQuotes()

    // NETWORK
    override suspend fun getAllQuotesFromNetwork(): LiveData<List<Quotes>> {
        val response = api.getAllQuotes().execute()
        // add all to database
        val list = ArrayList<Quotes>()
        for (quote in response.body()?.results!!) {
            list.add(
                Quotes(
                    quote._id,
                    quote.content,
                    quote.author,
                    quote.tags[0]
                )
            )
        }
        dao.insertAllQuotes(list)
        return dao.getAllQuotes()
    }

    override suspend fun getRandomQuoteFromNetwork(): Quotes? {
        val response = api.getRandomQuotes().execute()
        val quote = response.body()!!
        return Quotes(quote._id, quote.content, quote.author, quote.tags[0])
    }

    private suspend fun addToDatabase(quotes: Quotes) = dao.insertQuote(quotes)
}