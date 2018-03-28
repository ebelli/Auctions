package com.ebelli.auctions.io


import com.ebelli.auctions.io.data.Item
import com.ebelli.auctions.io.data.Items
import com.ebelli.auctions.io.webservice.AuctionService
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val AUCTIONS_API_ENDPOINT = "http://fc-ios-test.herokuapp.com"


class AuctionApi: RetrofitFactory {
    override fun createRetrofit(client: OkHttpClient): Retrofit {
       return Retrofit.Builder()
               .baseUrl(AUCTIONS_API_ENDPOINT)
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .client(client)
               .build()
    }

    override fun setupClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor({ chain ->
                    val newRequest = chain
                            .request()
                            .newBuilder()
                            .build()
                    chain.proceed(newRequest)
                })
                .addInterceptor(logging)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()
    }

    val service: AuctionService by lazy { createRetrofit(setupClient()).create(AuctionService::class.java) }

    /*
    private val client by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
                .addInterceptor({ chain ->
                    val newRequest = chain
                            .request()
                            .newBuilder()
                            .build()
                    chain.proceed(newRequest)
                })
                .addInterceptor(logging)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()
    }

     val restAdapter by lazy {
        Retrofit.Builder()
            .baseUrl(AUCTIONS_API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
            .build()
    }
    */


    fun getAuctions() : Observable<Items> =
            service.getAuctions()

}


interface RetrofitFactory {
    fun createRetrofit(client: OkHttpClient): Retrofit
    fun setupClient(): OkHttpClient
}
