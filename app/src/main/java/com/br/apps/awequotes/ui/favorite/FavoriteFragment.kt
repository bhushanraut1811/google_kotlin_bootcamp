package com.br.apps.awequotes.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.apps.awequotes.R
import com.br.apps.awequotes.data.db.model.Quotes
import com.br.apps.awequotes.ui.base.BaseFragment
import com.br.apps.awequotes.ui.base.ViewModelFactory
import com.br.apps.awequotes.ui.quote.QuotesAdapter

class FavoriteFragment() : BaseFragment<FavoriteViewModel>(), QuotesAdapter.OnFavoriteClicked {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
        val progressBar = root.findViewById<ProgressBar>(R.id.progress_circular)

        val quoteTextView = root.findViewById<TextView>(R.id.tv_quote)
        val authorTextView = root.findViewById<TextView>(R.id.tv_quote_author)
        val favoriteImageView = root.findViewById<ImageView>(R.id.iv_fav)
        favoriteImageView.visibility = View.VISIBLE
        favoriteImageView.isEnabled = false

        recyclerView.visibility = View.GONE
        viewModel.data?.observe(viewLifecycleOwner, Observer {
            // set Adapter
            recyclerView.visibility = View.VISIBLE
            val adapter = QuotesAdapter(this, false)
            adapter.setData(it)
            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.adapter = adapter
            progressBar.visibility = View.GONE
        })

        // random quotes
        viewModel.randomQuote.observe(viewLifecycleOwner, Observer {
            favoriteImageView.isEnabled = true
            quoteTextView.text = it.content
            authorTextView.text = it.author
        })

        viewModel.randomQuoteStatus.observe(viewLifecycleOwner, Observer {
            if (it) {
                favoriteImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_filled))
            } else {
                favoriteImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border))
            }
        })

        favoriteImageView.setOnClickListener {
            if (favoriteImageView.drawable.constantState ==
                resources.getDrawable(R.drawable.ic_favorite_border).constantState
            ) {
                viewModel.addRandomQuoteToFavorites()
            } else {
                viewModel.removeRandomQuoteFromFavorites()
            }
        }

        return root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun createViewModel(): FavoriteViewModel {
        return ViewModelProvider(this, ViewModelFactory.factory)
            .get(FavoriteViewModel::class.java)
    }

    override fun onFavoriteClicked(quotes: Quotes) {
        // No implementation is required
    }
}
