package com.ebelli.auctions.com.ebelli.auctions.io.webservice

import com.ebelli.auctions.io.RetrofitFactory
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object MockSupport {

    fun mockRetrofit(server: MockWebServer): Retrofit {
        val  mockRetrofitFactory = MockRetrofitFactory(server)
        return mockRetrofitFactory.createRetrofit(mockRetrofitFactory.setupClient())

    }

    private class MockRetrofitFactory(val server: MockWebServer) : RetrofitFactory {

        override fun setupClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return OkHttpClient.Builder().addInterceptor(interceptor)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS).build()
        }

        override fun createRetrofit(client: OkHttpClient): Retrofit {
            val gSon = GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                    .create()

            return Retrofit.Builder().baseUrl(server.url("/")).client(client).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(gSon)).build()
        }
    }
}