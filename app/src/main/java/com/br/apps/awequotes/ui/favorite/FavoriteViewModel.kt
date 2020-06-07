package com.br.apps.awequotes.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.br.apps.awequotes.data.Repository
import com.br.apps.awequotes.data.db.model.Quotes
import com.br.apps.awequotes.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: Repository
) : BaseViewModel() {

    private val TAG = FavoriteViewModel::class.simpleName
    var data: LiveData<List<Quotes>>? = null
    var randomQuote: MutableLiveData<Quotes> = MutableLiveData()
    var randomQuoteStatus: MutableLiveData<Boolean> = MutableLiveData()

    init {
        viewModelScope.launch(exceptionHandler + Dispatchers.IO) {
            data = repository.getAllFavQuotes()
            val quote = repository.getRandomQuoteFromNetwork()
            launch(Dispatchers.Main) {
                if (quote != null) {
                    randomQuote.value = quote
                }
            }
        }
    }

    fun addRandomQuoteToFavorites() {
        viewModelScope.launch((Dispatchers.IO)) {
            repository.addRandomQuoteToDb(randomQuote.value!!)
            launch(Dispatchers.Main) {
                randomQuoteStatus.value = true
            }
        }
    }

    fun removeRandomQuoteFromFavorites() {
        viewModelScope.launch((Dispatchers.IO)) {
            repository.removeRandomQuoteFromDb(randomQuote.value!!)
            launch(Dispatchers.Main) {
                randomQuoteStatus.value = false
            }
        }
    }
}