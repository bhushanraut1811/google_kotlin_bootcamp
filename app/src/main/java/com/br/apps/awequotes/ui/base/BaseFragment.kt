package com.br.apps.awequotes.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment<T : BaseViewModel>() : Fragment() {

    open override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }

    open lateinit var viewModel: T

    abstract fun createViewModel(): T


    override fun onResume() {
        super.onResume()
        viewModel.networkError.observe(viewLifecycleOwner, Observer {
            onNetworkError()
        })
    }


    fun onNetworkError() {
        (activity as MainActivity).showToastMessage(
            "Network Error",
            BaseActivity.MessageDuration.SHORT
        )
    }
}