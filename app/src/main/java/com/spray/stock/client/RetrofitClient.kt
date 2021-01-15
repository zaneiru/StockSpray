package com.spray.stock.client

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        private var retrofit: Retrofit? = null

        fun get(baseUrl: String): Retrofit? {
            if (retrofit == null) {
                val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
                val builder: OkHttpClient.Builder = OkHttpClient.Builder()
                with(builder) {
                    addInterceptor(loggingInterceptor)
                    connectTimeout(3, TimeUnit.SECONDS)
                    readTimeout(500, TimeUnit.MILLISECONDS)
                    writeTimeout(800, TimeUnit.MILLISECONDS)
                }

                val gson: Gson = GsonBuilder().setLenient().create()
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(builder.build())
                    .build()
            }

            return retrofit
        }
    }
}