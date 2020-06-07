package com.br.apps.awequotes.ui.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.apps.awequotes.ui.favorite.FavoriteViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

public open class BaseViewModel() : ViewModel() {

    private val TAG = FavoriteViewModel::class.simpleName
    open var networkError: MutableLiveData<Boolean> = MutableLiveData()

    open val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e(TAG, throwable.message!!)
        networkError.postValue(true)
    }

}