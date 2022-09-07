package com.udevapp.data.api

import com.udevapp.data.api.repository.login.LoginLocalDataSource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Api (private val loginLocalDataSource: LoginLocalDataSource) {

    companion object {
        private const val BASE_URL = "http://192.168.0.63/api/"
    }

    fun <Api> create(
        api: Class<Api>,
        authToken: String? = loginLocalDataSource.getToken(),
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
                            it.addHeader("Accept", "application/json")
                        }.build())
                    }
                    .connectTimeout(3,TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }
}