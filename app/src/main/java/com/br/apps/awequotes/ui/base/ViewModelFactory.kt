package com.br.apps.awequotes.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.br.apps.awequotes.data.Repository
import com.br.apps.awequotes.data.RepositoryImpl
import com.br.apps.awequotes.data.db.AppDatabase
import com.br.apps.awequotes.data.network.QuotesService
import com.br.apps.awequotes.ui.favorite.FavoriteViewModel
import com.br.apps.awequotes.ui.quote.QuotesViewModel

class ViewModelFactory() : ViewModelProvider.Factory {

    private lateinit var repository: Repository

    companion object {
        public var factory: ViewModelFactory = ViewModelFactory()
    }

    public fun injectDependency(service: QuotesService, database: AppDatabase): Unit {
        repository = RepositoryImpl(database.quotesDao(), service.getQuotesApi())
    }


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuotesViewModel::class.java)) {
            return modelClass.getConstructor(Repository::class.java)
                .newInstance(this.repository)
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return modelClass.getConstructor(Repository::class.java)
                .newInstance(this.repository)
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return modelClass.getConstructor().newInstance()
        } else {
            throw IllegalStateException("View model should be of type BaseViewModel");
        }
    }
}