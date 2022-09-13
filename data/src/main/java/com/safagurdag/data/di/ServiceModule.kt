package com.safagurdag.data.di

import android.content.Context
import com.safagurdag.data.api.theevapi.TheEVApiClient
import com.safagurdag.data.api.theevapi.TheEVApiService
import com.safagurdag.data.utils.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun provideThEVApiService(
        apiClient: TheEVApiClient
    ): TheEVApiService =
        apiClient.retrofit().create(TheEVApiService::class.java)

    @Provides
    fun provideNetworkUtil(@ApplicationContext context: Context) = NetworkUtil(context)

}