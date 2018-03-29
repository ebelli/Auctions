package com.ebelli.auctions.io.webservice

import com.ebelli.auctions.com.ebelli.auctions.io.webservice.MockSupport
import com.ebelli.auctions.io.data.Items
import io.reactivex.observers.TestObserver
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.apache.commons.io.IOUtils
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException


/**
 * Auction Service Endpoint Test
 */
class AuctionTest {

    private lateinit var server: MockWebServer
    private lateinit var service: AuctionService
    private var  observer = TestObserver<Items>()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        server = MockWebServer()
        server.start()
        val mockRetrofit = MockSupport.mockRetrofit(server)
        service = mockRetrofit.create(AuctionService::class.java)

    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server.shutdown()
    }

    @Test
    @Throws(IOException::class)
    fun testAuctions() {
        val io = this::class.java.classLoader.getResourceAsStream("auctions.txt")
        val body = IOUtils.toString(io)

        val mockResponse = MockResponse()
                .addHeader("Content-Type: text/plain; charset=utf-8")
                .setResponseCode(200)
                .setBody(body)

        server.enqueue(mockResponse)

        service.getAuctions().subscribe(observer)
        observer.assertNoErrors()
        observer.assertValueCount(1)
        val items = observer.values()
        assertNotNull(items)
        val itemList = items[0].items
        assertNotNull(itemList)

        assertNotNull(itemList)
        assertEquals(13, itemList.size)
        assertEquals("Hayes-Tillman", itemList[0].title)
        assertEquals(0.07, itemList[1].rate)
        assertEquals("A+", itemList[2].risk_band)
        assertEquals(10000000, itemList[5].amount_cents)

    }
}
