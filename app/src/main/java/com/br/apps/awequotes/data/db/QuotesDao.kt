package com.br.apps.awequotes.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.br.apps.awequotes.data.db.model.Quotes

@Dao
interface QuotesDao {

    @Query("SELECT * FROM quotes_table ORDER BY author ASC")
    fun getAllQuotes(): LiveData<List<Quotes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertQuote(quotes: Quotes)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllQuotes(plants: List<Quotes>)

    @Query("DELETE FROM quotes_table")
    suspend fun deleteAllQuotes()

    @Query("SELECT * FROM quotes_table WHERE is_fav=:isFav")
    fun getAllFavoriteQuotes(isFav: Boolean = false): LiveData<List<Quotes>>

    @Query("UPDATE quotes_table SET is_fav=:isFav WHERE id=:id")
    fun updateFavorite(isFav: Boolean, id: String): Int

    @Delete
    suspend fun deleteRandomQuote(quotes: Quotes)

}