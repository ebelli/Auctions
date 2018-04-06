package com.ebelli.auctions.app

import java.text.NumberFormat
import java.util.*


fun String.addCurrency(): String {
    return Currency.getInstance(Locale.getDefault()).symbol + " " + this
}

fun Number.formatAmount(): String {
    return NumberFormat.getNumberInstance(Locale.getDefault()).format(this).addCurrency()

}