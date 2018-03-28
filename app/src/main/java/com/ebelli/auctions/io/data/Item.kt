package com.ebelli.auctions.io.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Item:{
"id": 4,
"title": "Brown, Bins and Lakin",
"rate": 0.15,
"amount_cents": 2000000,
"term": 12,
"risk_band": "B",
"close_time": "2018-03-25T21:27:53.956Z"
}

 */
@Parcelize
data class Item(@Expose val id: Int,
                @Expose val title: String,
                @Expose val rate: Double,
                @Expose val amount_cents: Int,
                @Expose val term: Int,
                @Expose val risk_band: String,
                @Expose val close_time: Date): Parcelable