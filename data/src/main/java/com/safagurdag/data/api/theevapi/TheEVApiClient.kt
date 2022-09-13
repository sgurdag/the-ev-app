package com.safagurdag.data.api.theevapi

import com.safagurdag.data.api.ApiClient
import com.safagurdag.data.api.common.addLogger
import com.safagurdag.data.api.common.addQueryParameters
import com.safagurdag.data.api.common.createHttpClient
import com.safagurdag.data.api.common.createMoshiConverterFactoryOrDefault
import okhttp3.OkHttpClient
import retrofit2.Converter
import javax.inject.Inject

open class TheEVApiClient @Inject constructor() : ApiClient() {

    private val defaultRequestsQueryParams = mapOf<String, String>()

    override val baseUrl: String get() = "https://raw.githubusercontent.com/rentELtd/iostest/master/"
    override val converterFactory: Converter.Factory get() = createMoshiConverterFactoryOrDefault()
    override val httpClient: OkHttpClient?
        get() = createHttpClient()
            .addQueryParameters(defaultRequestsQueryParams)
            .addLogger()
            .build()

}