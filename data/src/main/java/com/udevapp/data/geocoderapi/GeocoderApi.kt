package com.udevapp.data.geocoderapi

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class GeocoderApi {
    companion object {
        private const val BASE_URL = "https://geocode-maps.yandex.ru/1.x/"
    }

    fun <Api> create(
        api: Class<Api>,
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(
                            chain.request().newBuilder().url(
                                chain.request().url
                                    .newBuilder()
                                    .addQueryParameter(
                                        "apikey",
                                        "9dc7234c-2b85-4127-a2af-497818130149"
                                    )
                                    .addQueryParameter("format", "json")
                                    .build()
                            ).build()
                        )
                    }
                    .connectTimeout(3, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}