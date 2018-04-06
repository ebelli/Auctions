package com.ebelli.auctions.app.adapter

import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ebelli.auctions.R
import com.ebelli.auctions.app.activity.AUCTION_ITEM
import com.ebelli.auctions.app.activity.AuctionActivity
import com.ebelli.auctions.app.formatAmount
import com.ebelli.auctions.io.data.Item
import com.ebelli.auctions.io.data.Items
import kotlinx.android.synthetic.main.view_item_auction.view.*
import java.util.*

/**
 *
 */

class AuctionAdapter(private val activity: AppCompatActivity): RecyclerView.Adapter<ItemViewHolder>() {

    private var items: ArrayList<Item> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item_auction, parent,false))


    override fun getItemCount() =  items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.apply {
            view_item_auction_title.text = items[position].title
            view_item_auction_risk_band.text = items[position].risk_band
            view_item_auction_amount.text = (items[position].amount_cents / 100).formatAmount()
            view_item_auction_container.setOnClickListener {
                val options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, view_item_auction_title, activity.getString(R.string.title_transition))
                context.startActivity(
                        Intent(context,AuctionActivity::class.java).putExtra(AUCTION_ITEM, items[position]),options.toBundle())
            }
        }
    }

    fun setData(items: Items){
        this.items.clear()
        this.items.addAll(items.items)
        notifyDataSetChanged()
    }
}

class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v)
