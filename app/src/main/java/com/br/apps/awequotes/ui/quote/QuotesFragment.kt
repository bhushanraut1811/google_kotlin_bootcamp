package com.br.apps.awequotes.ui.quote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.apps.awequotes.R
import com.br.apps.awequotes.data.db.model.Quotes
import com.br.apps.awequotes.ui.base.BaseFragment
import com.br.apps.awequotes.ui.base.ViewModelFactory

class QuotesFragment() : BaseFragment<QuotesViewModel>(), QuotesAdapter.OnFavoriteClicked {

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var adapter: QuotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_quotes, container, false)
        recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
        progressBar = root.findViewById<ProgressBar>(R.id.progress_circular)
        recyclerView.visibility = View.GONE
        adapter = QuotesAdapter(this, true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        viewModel.mediatorLiveData.observe(viewLifecycleOwner, Observer {
            recyclerView.visibility = View.VISIBLE
            adapter.setData(it.value)
            progressBar.visibility = View.GONE
        })

        return root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun createViewModel(): QuotesViewModel {
        return ViewModelProvider(this, ViewModelFactory.factory)
            .get(QuotesViewModel::class.java)
    }

    override fun onFavoriteClicked(quotes: Quotes) {
        viewModel.addToFavorite(quotes)
    }
}
