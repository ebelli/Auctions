package com.ebelli.auctions.app.viewmodel

import android.arch.lifecycle.ViewModel
import com.ebelli.auctions.io.AuctionApi
import com.ebelli.auctions.io.data.Item
import com.ebelli.auctions.io.data.Items
import io.reactivex.Observable

/**

 */
class MainViewModel: ViewModel() {

    fun loadAuctions(): Observable<Items> {
        return AuctionApi().getAuctions()
    }
}