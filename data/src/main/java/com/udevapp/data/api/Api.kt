package com.udevapp.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    companion object {
        private const val BASE_URL = "http://192.168.0.63/api/"
    }

    fun <Api> create(
        api: Class<Api>,
        authToken: String? = "", //loginLocalDataSource.getToken().token,
        authType: String? = "Bearer"
    ): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            if (authType != null) {
                                it.addHeader("Authorization", "$authType $authToken")
                            }
                        }.build())
                    }
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}