package com.ebelli.auctions.app.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.ebelli.auctions.R
import com.ebelli.auctions.app.addCurrency
import com.ebelli.auctions.io.data.Item
import com.ebelli.auctions.model.EstimatedReturnAmount
import kotlinx.android.synthetic.main.activity_auction.*

const val AUCTION_ITEM ="auction_item"

class AuctionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auction)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val auctionItem = intent.extras.getParcelable<Item>(AUCTION_ITEM)
        auctionItem?.apply {
            activity_auction_title.text = this.title
            activity_auction_rate.text = risk_band
            activity_auction_amount.text = (amount_cents / 100).toString().addCurrency()
            activity_auction_estimated_return.text = String.format("%.2f", EstimatedReturnAmount.getEstimatedReturnAmount(rate, risk_band))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
            }
        }
        return true
    }
}
