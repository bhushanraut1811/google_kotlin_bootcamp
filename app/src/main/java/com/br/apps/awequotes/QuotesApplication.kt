package com.br.apps.awequotes

import android.app.Application
import com.br.apps.awequotes.data.db.AppDatabase
import com.br.apps.awequotes.data.network.QuotesService
import com.br.apps.awequotes.ui.base.ViewModelFactory

class QuotesApplication() : Application() {

    override fun onCreate() {
        super.onCreate()
        val db = AppDatabase.getDatabase(this)
        val service = QuotesService.quotesService
        ViewModelFactory.factory.injectDependency(service, db)
    }
}