package com.ebelli.auctions.io.webservice

import com.ebelli.auctions.io.data.Item
import com.ebelli.auctions.io.data.Items
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Auction Service Endpoints
 */
interface AuctionService {


    /**
     * Get a list of Auctions
     *
     * @return a list of 'items'
     */
    @GET("/auctions")
    fun getAuctions(): Observable<Items>
}