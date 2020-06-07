package com.br.apps.awequotes.ui.quote

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.br.apps.awequotes.R
import com.br.apps.awequotes.data.db.model.Quotes

class QuotesAdapter(private val listener: OnFavoriteClicked, private val shouldShowFavorite: Boolean) :
    RecyclerView.Adapter<QuotesAdapter.DayViewHolder>() {

    private var quotes: List<Quotes>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_quotes_view, parent, false)
        )
    }

    override fun getItemCount(): Int = quotes!!.size

    override fun onBindViewHolder(viewHolder: DayViewHolder, position: Int) {
        if (shouldShowFavorite) {
            viewHolder.itemView.findViewById<ImageView>(R.id.iv_fav).visibility = VISIBLE
            viewHolder.itemView.findViewById<ImageView>(R.id.iv_fav).setOnClickListener {
                viewHolder.itemView.findViewById<ImageView>(R.id.iv_fav)
                    .setImageDrawable(viewHolder.itemView.resources.getDrawable(R.drawable.ic_favorite_filled))
                listener.onFavoriteClicked(quotes!![position])
            }
        }
        if (quotes!!.get(position).isFav) {
            viewHolder.itemView.findViewById<ImageView>(R.id.iv_fav)
                .setImageDrawable(viewHolder.itemView.resources.getDrawable(R.drawable.ic_favorite_filled))
        } else {
            viewHolder.itemView.findViewById<ImageView>(R.id.iv_fav)
                .setImageDrawable(viewHolder.itemView.resources.getDrawable(R.drawable.ic_favorite_border))
        }

        viewHolder.bindView(quotes!!.get(position))
    }

    fun setData(quotes: List<Quotes>?) {
        this.quotes = quotes
        notifyDataSetChanged()
    }

    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(quotes: Quotes) {
            itemView.findViewById<TextView>(R.id.tv_quote).text = quotes.content
            itemView.findViewById<TextView>(R.id.tv_quote_author).text = "- " + quotes.author
            if (quotes.isFav) {
                itemView.findViewById<ImageView>(R.id.iv_fav)
                    .setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_favorite_filled))
            }
        }
    }

    interface OnFavoriteClicked {
        fun onFavoriteClicked(quotes: Quotes)
    }
}


