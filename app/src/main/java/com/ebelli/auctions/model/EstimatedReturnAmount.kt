package com.ebelli.auctions.model

/**

Calculate the estimated return amount:

era = (1 + ar - ebd - f) X ba

where:
- era is the estimated return amount in GBP
- ar is the auction's rate
- edb is the estimated bad debt associated to the auction's risk band (see table)
- f is the fee, default is 0.01
- ba is the bid amount, default is £20

The estimated bad debt value depends on the risk band:
Risk band Estimated bad debt
A+ 0.01
A  0.02
B  0.03
C  0.04
C- 0.05
 */

private const val ESTIMATED_BAD_DEBT_DEFAULT_VALUE = 0.05
private const val BID_AMOUNT_DEFAULT_VALUE = 20.0
private const val FEE_DEFAULT_VALUE = 0.01

private val estimatedBadDebt = mapOf(
        Pair("A+",0.01),
        Pair("A", 0.02),
        Pair("B", 0.03),
        Pair("C", 0.04),
        Pair("C-",0.05))

object EstimatedReturnAmount {

    fun getEstimatedBadDebt(riskBand: String) = estimatedBadDebt[riskBand.toUpperCase()] ?: ESTIMATED_BAD_DEBT_DEFAULT_VALUE

    fun getEstimatedReturnAmount(auctionRate: Double,
                                 riskBand: String,
                                 fee: Double = FEE_DEFAULT_VALUE,
                                 bidAmount: Double = BID_AMOUNT_DEFAULT_VALUE): Double =
         (1 + auctionRate - getEstimatedBadDebt(riskBand) - fee) * bidAmount
}