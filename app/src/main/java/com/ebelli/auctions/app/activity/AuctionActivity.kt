package com.ebelli.auctions.app.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ebelli.auctions.R
import com.ebelli.auctions.io.data.Item
import com.ebelli.auctions.model.EstimatedReturnAmount
import kotlinx.android.synthetic.main.activity_auction.*
import java.util.*

const val AUCTION_ITEM ="auction_item"

class AuctionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auction)
        val auctionItem = intent.extras.getParcelable<Item>(AUCTION_ITEM)
        auctionItem?.apply {
            activity_auction_title.text = this.title
            activity_auction_rate.text = risk_band
            activity_auction_amount.text = Currency.getInstance(Locale.getDefault()).symbol + (amount_cents / 100).toString()
            activity_auction_estimated_return.text = EstimatedReturnAmount.getEstimatedReturnAmount(rate, risk_band).toString()
        }
    }
}
