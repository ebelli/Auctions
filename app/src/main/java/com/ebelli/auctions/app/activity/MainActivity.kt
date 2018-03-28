package com.ebelli.auctions.app.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.ebelli.auctions.R
import com.ebelli.auctions.app.adapter.AuctionAdapter
import com.ebelli.auctions.app.viewmodel.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        activity_main_auctions.layoutManager = linearLayoutManager
        val adapter = AuctionAdapter()
        activity_main_auctions.adapter = adapter

        model.loadAuctions().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe({
            adapter.setData(it)
        })

    }
}
