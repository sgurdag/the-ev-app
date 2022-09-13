package com.safagurdag.data.api.theevapi

import com.safagurdag.domain.model.User
import retrofit2.http.GET
import retrofit2.Response

interface TheEVApiService {

    @GET("user.json")
    suspend fun userDetail(): Response<User>

}