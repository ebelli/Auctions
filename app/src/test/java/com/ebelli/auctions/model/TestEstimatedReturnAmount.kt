package com.ebelli.auctions.model

import com.ebelli.auctions.model.EstimatedReturnAmount.getEstimatedBadDebt
import com.ebelli.auctions.model.EstimatedReturnAmount.getEstimatedReturnAmount
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Created by enzobelli on 27/03/2018.
 */
class TestEstimatedReturnAmount {

    @Test
    fun testGetEstimatedBadDebt(){
        assertEquals(0.02,getEstimatedBadDebt("A"))
        assertEquals(0.03,getEstimatedBadDebt("B"))
    }

    @Test
    fun testNotExistingEstimatedBadDebt(){
        assertEquals(0.05,getEstimatedBadDebt("D"))
    }

    @Test
    fun testEstimatedReturnAmount(){
        assertEquals(23.4, getEstimatedReturnAmount(0.2,"A"))
        assertEquals(11.7, getEstimatedReturnAmount(0.2,"A", bidAmount = 10.0))
        assertEquals(20.0, getEstimatedReturnAmount(0.03,"A"))
        assertEquals(40.0, getEstimatedReturnAmount(1.08,"C", fee = 0.04))
    }
}