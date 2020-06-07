package com.br.apps.awequotes.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<T : BaseViewModel>() : AppCompatActivity() {

    open override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    open lateinit var viewModel: T

    abstract fun createViewModel(): T


    fun showToastMessage(msg: String, duration: MessageDuration): Unit {
        Toast.makeText(this, msg, duration.value).show()
    }

    enum class MessageDuration(val value: Int) {
        LONG(Toast.LENGTH_SHORT),
        SHORT(Toast.LENGTH_SHORT)
    }
}