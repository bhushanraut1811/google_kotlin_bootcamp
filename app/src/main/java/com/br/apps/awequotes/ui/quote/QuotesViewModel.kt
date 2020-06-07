package com.br.apps.awequotes.ui.quote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.br.apps.awequotes.data.Repository
import com.br.apps.awequotes.data.db.model.Quotes
import com.br.apps.awequotes.ui.base.BaseViewModel
import com.br.apps.awequotes.ui.favorite.FavoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuotesViewModel(private val repository: Repository) : BaseViewModel() {

    private val TAG = FavoriteViewModel::class.simpleName

    var mediatorLiveData = MediatorLiveData<LiveData<List<Quotes>>>()

    init {
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            val data = repository.getAllQuotesFromNetwork()
            if (data != null) {
                launch(Dispatchers.Main) {
                    mediatorLiveData.addSource(data) { mediatorLiveData.value = data }
                }
            }
        }
    }

    fun addToFavorite(quotes: Quotes) {
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            repository.addToFavorites(quotes)
        }
    }
}