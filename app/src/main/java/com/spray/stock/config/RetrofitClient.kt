package com.spray.stock.config

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
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
                val loggingInterceptor = HttpLoggingInterceptor { message ->
                    Log.d("TAG", "message: $message")
                }
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                val builder: OkHttpClient.Builder = OkHttpClient.Builder()
                val dispatcher = Dispatcher()
                dispatcher.maxRequests = 1

                with(builder) {
                    addInterceptor(loggingInterceptor)
                    connectTimeout(3, TimeUnit.SECONDS)
                    readTimeout(500, TimeUnit.MILLISECONDS)
                    writeTimeout(800, TimeUnit.MILLISECONDS)
                    addInterceptor(loggingInterceptor)
                    dispatcher(dispatcher)
                }

                val gson: Gson = GsonBuilder().create()
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