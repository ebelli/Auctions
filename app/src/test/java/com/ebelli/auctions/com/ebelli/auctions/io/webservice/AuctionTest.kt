package com.ebelli.auctions.io.webservice

import com.ebelli.auctions.com.ebelli.auctions.io.webservice.MockSupport
import com.ebelli.auctions.io.AuctionApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.apache.commons.io.IOUtils
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.modules.junit4.rule.PowerMockRule
import java.io.IOException


/**
 * Auction Service Endpoint Test
 */
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*", "javax.crypto.*", "javax.net.ssl.*", "javax.xml.*")
class AuctionTest {

class AnnouncementApiTest {

    @get:Rule
    var rule = PowerMockRule()

//    @get:Rule
//    var mockRule = MockitoRule()

    private lateinit var server: MockWebServer
    private lateinit var service: AuctionApi
 //   private lateinit var  observer : TestSubscriber<List<Item>>


    @Before
    @Throws(Exception::class)
    fun setUp() {
        server = MockWebServer()
        server.start()
        val mockRetrofit = MockSupport.mockRetrofit(server)
        service = mockRetrofit.create(AuctionApi::class.java)

    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        server.shutdown()
    }

    @Test
    @Throws(IOException::class)
    fun testAAuctions() {
        val io = this.javaClass.getResourceAsStream("announcement_list.txt")
        val body = IOUtils.toString(io)

        val mockResponse = MockResponse()
                .addHeader("Content-Type: text/plain; charset=utf-8")
                .setResponseCode(200)
                .setBody(body)

        server.enqueue(mockResponse)

//        observer.assertNoErrors()
//        observer.assertCompleted()

        // Create a MockRetrofit object with a NetworkBehavior which manages the fake behavior of calls.

        //When
 //       service.service.getAuctions().subscribe(observer)


/*
        val announcementLists = observer.onNextEvents
        assertNotNull(announcementLists)
        assertEquals(1, announcementLists.size.toLong())

        val dataAnnouncementResponse = announcementLists[0]
        assertNotNull(dataAnnouncementResponse)

        val announcements = dataAnnouncementResponse.data.announcements
        assertNotNull(announcements)
        assertEquals(3, announcements.size.toLong())

        val announcement = announcements[2]
        assertNotNull(announcement)
        assertEquals(2, announcement.announcementId.toLong())
        assertEquals(99, announcement.retailerId.toLong())
        assertEquals("Double Points Fun ", announcement.name)
        assertEquals("Double Points Fun ", announcement.title)
        assertEquals("Earn double points because it's fun!", announcement.subtitle)
        assertEquals("double points are super super fun double points are super super fun double points are super super fun double points are super super fun double points are super super fun double points are super super fun double points are super", announcement.description)
        assertNotNull(announcement.image)
        assertEquals(getDateFromString("2017-07-30T13:55:50Z"), announcement.visibleFrom)
        assertEquals(getDateFromString("2018-07-31T13:55:54Z"), announcement.visibleTo)
        assertEquals(false, announcement.displayVisibleTo)
        assertEquals("", announcement.callToActionTitle)
        assertEquals("", announcement.callToActionLink)
        assertEquals(null, announcement.dismissedAt)
        assertFalse(announcement.isBrandAnnouncement)
        val brandAnnouncement = announcements[1]
        assertTrue(brandAnnouncement.isBrandAnnouncement)


    }
     */
}
}
}