package com.ebelli.auctions.app

import java.util.*


fun String.addCurrency(): String {
    return Currency.getInstance(Locale.getDefault()).symbol + " " + this

}